package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.CellDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.MatrixDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.RowDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.ValueDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa.CurrMapMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.PerfIndicator;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.CurrMapRequestStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.CurrMapRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrMapServiceImpl implements CurrMapService {

    private final String TABLE_NAME = "CurrMap";

    private final CurrMapRepository currMapRepository;
    private final CurrMapMapper currMapMapper;
    private final UserProvider userProvider;
    private final ChangeLogService changeLogService;

    @Override
    public List<CurrMap> getAllCurrMapByAcadProgCurrIdAndPerfIndIdAndCourseId(long acadProgCurrId, long perfIndId,
            long courseId, long acadProgId) {
        return currMapRepository.getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(
                acadProgCurrId, perfIndId,
                courseId, acadProgId);
    }

    @Transactional
    @Override
    public MatrixDTO getMatrixDTO(long acadProgCurrId, long acadProgId, List<StudOutcome> studOutcomes,
            List<Course> courses) {
        List<List<Map<String, String>>> matrix = buildMatrixCells(acadProgCurrId, acadProgId, studOutcomes, courses);
        return convertMatrixToMatrixDTO(matrix);
    }

    @Override
    @Transactional
    public void updateSuggestedCurrMapRequestStatus(long facultyId, long programId, long acadProgCurrId, long prevCurrMapId, long suggestedCurrMapId, String destinationState) {
        CurrMap suggestedCurrMap = getCurrMapById(suggestedCurrMapId);
        switch (destinationState) {
            case "APPROVED" -> approveCurrMap(prevCurrMapId, suggestedCurrMap, destinationState);
            case "PENDING" -> unapproveCurrMap(prevCurrMapId, suggestedCurrMap, destinationState);
            case "REJECTED" -> rejectCurrMap(prevCurrMapId, suggestedCurrMap, destinationState);
            default -> throw new IllegalArgumentException("Invalid destination state");
        }
    }

    private void approveCurrMap(long prevCurrMapId, CurrMap suggestedCurrMap, String destinationState) {
        if (!suggestedCurrMap.getRequestState().equals(destinationState)) {
            CurrMap prevCurrMap = getCurrMapById(prevCurrMapId);
            setCurrMapState(CurrMapRequestStatus.REPLACED.getKey(), prevCurrMap);
            suggestedCurrMap.setCmAcceptedDate(new Date());
            suggestedCurrMap.setCmRejectedDate(null);
            setCurrMapState(destinationState, suggestedCurrMap);
            changeLogService.addChange(ChangeLogAction.UPDATE, String.valueOf(suggestedCurrMap.getCmId()), TABLE_NAME,
                    suggestedCurrMap, prevCurrMap);
            return;
        }
        throw new IllegalStateException("Curr map was already approved");
    }

    private void unapproveCurrMap(long prevCurrMapId, CurrMap suggestedCurrMap, String destinationState) {
        CurrMap prevCurrMap = getCurrMapById(prevCurrMapId);
        suggestedCurrMap.setCmAcceptedDate(null);
        suggestedCurrMap.setCmRejectedDate(null);
        setCurrMapState(destinationState, suggestedCurrMap);
        changeLogService.addChange(ChangeLogAction.UPDATE, String.valueOf(suggestedCurrMap.getCmId()), TABLE_NAME,
                suggestedCurrMap, prevCurrMap);
    }

    private void rejectCurrMap(long prevCurrMapId, CurrMap suggestedCurrMap, String destinationState) {
        if (!suggestedCurrMap.getRequestState().equals(destinationState)) {
            CurrMap prevCurrMap = getCurrMapById(prevCurrMapId);
            suggestedCurrMap.setCmRejectedDate(new Date());
            suggestedCurrMap.setCmAcceptedDate(null);
            setCurrMapState(destinationState, suggestedCurrMap);
            changeLogService.addChange(ChangeLogAction.UPDATE, String.valueOf(suggestedCurrMap.getCmId()), TABLE_NAME,
                    suggestedCurrMap, prevCurrMap);
            return;
        }
        throw new IllegalStateException("Curr map was already rejected");
    }

    private void setCurrMapState(String destinationState, CurrMap currMap) {
        currMap.setRequestState(CurrMapRequestStatus.valueOf(destinationState).toString());

        if (!destinationState.equals(CurrMapRequestStatus.REPLACED.getKey()))
            currMap.setUser2(userProvider.getUserFromSession());

        currMapRepository.save(currMap);
    }

    private CurrMap getCurrMapById(long currMapId) {
        return currMapRepository.findById(currMapId).orElseThrow();
    }

    private List<List<Map<String, String>>> buildMatrixCells(long acadProgCurrId, long acadProgId,
            List<StudOutcome> studOutcomes, List<Course> courses) {
        //Order student outcumes and courses
        studOutcomes.sort(Comparator.comparing(StudOutcome::getSoOrdinalNumber));
        courses.sort((course1, course2) -> {
            int semesterComparison = course1.getSemester().getSemName().compareTo(course2.getSemester().getSemName());
            if (semesterComparison == 0) {
                return course1.getCourseNameEng().compareTo(course2.getCourseNameEng());
            } else {
                return semesterComparison;
            }
        });
        List<List<Map<String, String>>> matrix = new ArrayList<>();
        Map<String, Integer> indexById = new HashMap<>();
        addPerfIndicatorsToMatrix(matrix, studOutcomes, indexById);
        int numberOfPerfIndicators = 0;
        for (StudOutcome studOutcome : studOutcomes) {
            numberOfPerfIndicators += studOutcome.getPerfIndicators().size();
        }
        addSemestersAndCoursesToMatrix(matrix, courses, numberOfPerfIndicators, indexById);
        addCurrMapsToMatrix(studOutcomes, courses, acadProgCurrId, acadProgId, matrix, indexById);
        return matrix;
    }

    private void addPerfIndicatorsToMatrix(List<List<Map<String, String>>> matrix,
            List<StudOutcome> orderedStudOutcomes, Map<String, Integer> indexById) {
        List<Map<String, String>> perfIndicatorsRow = new ArrayList<>();
        if (orderedStudOutcomes.isEmpty())
            return;
        //The matrix has two empty cells at the beginning of the performance indicators row
        perfIndicatorsRow.add(new HashMap<>());
        perfIndicatorsRow.add(new HashMap<>());
        List<PerfIndicator> perfIndicators = getOrderedPerfIndicatorsFromStudOutcomes(orderedStudOutcomes);
        for (PerfIndicator perfIndicator : perfIndicators) {
            perfIndicator.initializeCellValues();
            int index = perfIndicatorsRow.size();
            perfIndicatorsRow.add(perfIndicator.getValues());
            indexById.put("pi" + perfIndicator.getPiId(), index);
        }
        matrix.add(perfIndicatorsRow);
    }

    private void addSemestersAndCoursesToMatrix(List<List<Map<String, String>>> matrix, List<Course> orderedCourses,
            int numberOfPerfIndicators, Map<String, Integer> indexById) {
        for (Course course : orderedCourses) {
            List<Map<String, String>> row = new ArrayList<>();
            //The row has the semester number and the course name at the beginning
            course.getSemester().initializeCellValues();
            course.initializeCellValues();
            row.add(course.getSemester().getValues());
            row.add(course.getValues());
            //The other rows are empty cells
            for (int i = 0; i < numberOfPerfIndicators; i++) {
                row.add(new HashMap<>());
            }
            int index = matrix.size();
            matrix.add(row);
            indexById.put("c" + course.getCourseId(), index);
        }
    }

    private void addCurrMapsToMatrix(List<StudOutcome> studOutcomes, List<Course> courses, long acadProgCurrId,
            long acadProgId, List<List<Map<String, String>>> matrix,
            Map<String, Integer> indexById) {
        List<PerfIndicator> perfIndicators = getOrderedPerfIndicatorsFromStudOutcomes(studOutcomes);
        for (PerfIndicator perfIndicator : perfIndicators) {
            for (Course course : courses) {
                List<CurrMap> currMap = getAllCurrMapByAcadProgCurrIdAndPerfIndIdAndCourseId(acadProgCurrId,
                        perfIndicator.getPiId(), course.getCourseId(), acadProgId);
                if (!currMap.isEmpty()) {
                    currMap.get(0).initializeCellValues();
                    int row = indexById.get("c" + course.getCourseId());
                    int column = indexById.get("pi" + perfIndicator.getPiId());
                    matrix.get(row).get(column).putAll(currMap.get(0).getValues());
                }
            }
        }
    }

    private MatrixDTO convertMatrixToMatrixDTO(List<List<Map<String, String>>> matrix) {
        List<RowDTO> rowsDTO = new ArrayList<>();
        for (List<Map<String, String>> row : matrix) {
            List<CellDTO> cellsDTO = new ArrayList<>();

            for (Map<String, String> cell : row) {
                List<ValueDTO> values = cell.entrySet().stream().map(currMapMapper::fromMapElementToValueDTO).toList();
                CellDTO cellDTO = CellDTO.builder()
                        .values(values)
                        .build();
                cellsDTO.add(cellDTO);
            }
            RowDTO rowDTO = RowDTO.builder()
                    .cells(cellsDTO)
                    .build();
            rowsDTO.add(rowDTO);
        }
        return MatrixDTO.builder()
                .matrix(rowsDTO)
                .build();
    }

    private List<PerfIndicator> getOrderedPerfIndicatorsFromStudOutcomes(List<StudOutcome> orderedStudOutcomes) {
        List<PerfIndicator> perfIndicators = new ArrayList<>();
        for (StudOutcome studOutcome : orderedStudOutcomes) {
            List<PerfIndicator> perfIndicatorsOfOneStudOutcome = studOutcome.getPerfIndicators();
            perfIndicatorsOfOneStudOutcome.sort(Comparator.comparing(PerfIndicator::getPiOrdinalNumber));
            perfIndicators.addAll(perfIndicatorsOfOneStudOutcome);
        }
        return perfIndicators;
    }
}