package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.FacultyMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.FacultyRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.FacultyProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;
    private final FacultyProvider facultyProvider;

    @Override
    @Transactional
    public FacultyOutDTO createFaculty(FacultyInDTO facultyInDTO) {
        return facultyProvider.saveFaculty(facultyInDTO);
    }

    @Transactional
    @Override
    public FacultyOutDTO getFacultyByFacId(long facId) {
        logger.info("Getting a faculty by its id.");
        facultyProvider.validateAccess(0L, UserPermAccess.QUERY);
        return facultyMapper.facultyToFacultyOutDTO(facultyProvider.findFacultyByFacId(facId));
    }

    @Transactional
    @Override
    public FacultyOutDTO getFacultyByFacNameInSpa(String name) {
        return facultyProvider.getFacultyByNameInSpa(name);
    }

    @Transactional
    @Override
    public FacultyOutDTO getFacultyByFacNameInEng(String name) {
        return facultyProvider.getFacultyByNameInEng(name);
    }
    @Transactional
    @Override
    public List<FacultyOutDTO> getFaculties() {
        logger.info("Getting all faculties of the system.");
        facultyProvider.validateAccess(0L,UserPermAccess.QUERY);
        return facultyMapper.facultiesToFacultiesOutDTO(facultyRepository.findAll());
    }
    @Transactional
    @Override
    public FacultyOutDTO updateFaculty(long facId, FacultyInDTO facultyToUpdate) {
        logger.info("Updating the faculty {}.", facId);
        facultyProvider.validateAccess(facId, UserPermAccess.ADMIN);

        facultyProvider.checkIfSpaNameIsAlreadyUsed(facultyToUpdate.facNameSpa());
        facultyProvider.checkIfEngNameIsAlreadyUsed(facultyToUpdate.facNameEng());

        Faculty faculty = facultyProvider.findFacultyByFacId(facId);
        FacultyOutDTO facultyBefore = facultyMapper.facultyToFacultyOutDTO(faculty);

        faculty.setFacIsActive(facultyToUpdate.isActive().charAt(0));
        faculty.setFacNameSpa(facultyToUpdate.facNameSpa());
        faculty.setFacNameEng(facultyToUpdate.facNameEng());

        facultyRepository.save(faculty);

        facultyProvider.addActionToChangelog(ChangeLogAction.UPDATE, facId,"FACULTY", faculty, facultyBefore);
        logger.info("Faculty successfully updated.");

        return facultyMapper.facultyToFacultyOutDTO(faculty);
    }

    @Transactional
    @Override
    public void deleteFaculty(long facId){
        logger.info("Deleting a faculty.");
        facultyProvider.validateAccess(facId, UserPermAccess.ADMIN);
        Faculty facultyToDelete = facultyProvider.findFacultyByFacId(facId);

        logger.info("Checking if the faculty has academic programs, courses or users associated.");
        if (facultyToDelete.getAcadPrograms().isEmpty() && facultyToDelete.getCourses().isEmpty()){

            facultyRepository.delete(facultyToDelete);
            facultyProvider.addActionToChangelog(ChangeLogAction.DELETE, facId,"FACULTY", null, facultyToDelete);
            logger.info("Faculty {} was successfully deleted.", facId);

        }else {
            logger.error("Error: a faculty can't be deleted if it has associated data.");
            throw new OutCurrException(OutCurrExceptionType.FACULTY_NOT_DELETED);
        }
    }
}