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
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;
    private final FacultyProvider facultyProvider;
    private final MeterRegistry meterRegistry;

    @Override
    @Transactional
    public FacultyOutDTO createFaculty(FacultyInDTO facultyInDTO) {
        // Agregar detalles contextuales al MDC
        MDC.put("operation", "createFaculty");
        MDC.put("method", "POST");

        logger.info("Starting method | facultyInDTO={}", facultyInDTO);

        try {
            // Guardar la facultad
            logger.debug("Saving faculty | facultyInDTO={}", facultyInDTO);
            FacultyOutDTO result = facultyProvider.saveFaculty(facultyInDTO);

            // Métrica de creación de facultad
            meterRegistry.counter("faculty.created").increment();

            logger.info("Successfully created faculty | facultyId={}, facultyName={}", result.facId(), facultyInDTO.facNameSpa());
            return result;

        } catch (Exception e) {
            logger.error("Error in createFaculty | facultyInDTO={}, message={}", facultyInDTO, e.getMessage(), e);
            throw e;

        } finally {
            // Limpiar el contexto MDC después de la ejecución
            MDC.clear();
        }
    }


    @Override
    @Transactional
    public FacultyOutDTO getFacultyByFacId(long facId) {
        // Agregar detalles contextuales al MDC
        MDC.put("operation", "getFacultyByFacId");
        MDC.put("method", "GET");

        logger.info("Starting method to get faculty by facultyId={}", facId);

        try {
            // Validación de acceso
            logger.debug("Validating access for facultyId={}", facId);

            // Obtener facultad
            logger.debug("Fetching faculty with facultyId={}", facId);
            FacultyOutDTO facultyOutDTO = facultyMapper.facultyToFacultyOutDTO(facultyProvider.findFacultyByFacId(facId));

            logger.info("Successfully retrieved faculty | facultyId={}, facultyName={}", facId, facultyOutDTO.facNameSpa());
            return facultyOutDTO;

        } catch (Exception e) {
            logger.error("Error in getFacultyByFacId | facultyId={}, message={}", facId, e.getMessage(), e);
            throw e;

        } finally {
            // Limpiar el contexto MDC después de la ejecución
            MDC.clear();
        }
    }


    @Transactional
    @Override
    public FacultyOutDTO getFacultyByFacNameInSpa(String name) {
        return facultyProvider.getFacultyByNameInSpa(name);
    }

    @Override
    @Transactional
    public FacultyOutDTO getFacultyByFacNameInEng(String name) {
        // Agregar detalles contextuales al MDC
        MDC.put("operation", "getFacultyByFacNameInEng");
        MDC.put("method", "GET");

        logger.info("Starting method to get faculty by name in English: {}", name);

        try {
            // Métrica
            FacultyOutDTO facultyOutDTO = meterRegistry.timer("faculty.getByName.eng").record(() -> facultyProvider.getFacultyByNameInEng(name));

            if (facultyOutDTO != null) {
                logger.info("Successfully retrieved faculty by name in English | facultyNameInEng={}", name);
            } else {
                logger.warn("No faculty found with name in English: {}", name);
            }

            return facultyOutDTO;

        } catch (Exception e) {
            logger.error("Error in getFacultyByFacNameInEng | facultyNameInEng={}, message={}", name, e.getMessage(), e);
            throw e;

        } finally {
            // Limpiar el contexto MDC después de la ejecución
            MDC.clear();
        }
    }



    @Transactional
    @Override
    public List<FacultyOutDTO> getFaculties() {
        logger.info("Getting all faculties of the system.");
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

    @Scheduled(fixedRate = 60000)
    public void logFacultyCount() {
        long count = facultyRepository.count();
        logger.info("Number of faculties in the system: {}", count);
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void dailyFacultyCheck() {
        logger.info("Executing daily faculty check.");

        List<Faculty> faculties = facultyRepository.findAll();

        logger.info("Faculties in the system: success");
    }
}