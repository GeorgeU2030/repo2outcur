package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.AcadProgramInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.AcadProgramMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AcadProgramRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.FacultyProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.AcadProgramValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType.AcadProgramPermStatus.CURRENT;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"acadProgram"})
public class AcadProgramServiceImpl implements AcadProgramService {
    //TODO add test coverage
    private static final Logger logger = LoggerFactory.getLogger(AcadProgramServiceImpl.class);

    private final AcadProgramValidator acadProgramValidator;

    private final AcadProgramRepository acadProgramRepository;

    private final FacultyProvider facultyProvider;

    private final AcadProgramMapper acadProgramMapper;

    private final MeterRegistry meterRegistry;

    //TODO the program should not assume the operations are for CURRENT programs. It should also support Future
    // and Inactive, filterin according to the parameter used as input. Use the logger for errors.

    @Transactional
    @Override
    public List<AcadProgram> getAcadProgramsByFaculty(long facultyId) {
        // Agregar detalles contextuales al MDC
        MDC.put("operation", "getAcadProgramsByFaculty");
        MDC.put("entityId", String.valueOf(facultyId));
        MDC.put("method", "GET");

        logger.info("Starting method | facultyId={}", facultyId);

        // Métrica de rendimiento
        return meterRegistry.timer("acadProgram.getByFaculty").record(() -> {
            try {
                // Validación de acceso
                logger.debug("Validating access | facultyId={}", facultyId);
                validateAccess(facultyId, 0L, UserPermAccess.QUERY, CURRENT);

                // Búsqueda de programas académicos
                logger.debug("Fetching academic programs | facultyId={}", facultyId);
                List<AcadProgram> programs = acadProgramRepository.findAllByFacultyFacId(facultyId);

                // Validación de resultado
                if (programs.isEmpty()) {
                    logger.warn("No academic programs found | facultyId={}", facultyId);
                    throw new OutCurrException(OutCurrExceptionType.FACULTY_INVALID_FAC_ID);
                }

                logger.info("Successfully retrieved programs | facultyId={}, count={}", facultyId, programs.size());
                return programs;

            } catch (OutCurrException e) {
                logger.error("Business error | facultyId={}, errorType={}, message={}",
                            facultyId, e.getCause(), e.getMessage(), e);
                throw e;

            } catch (Exception e) {
                logger.error("Unexpected error | facultyId={}, message={}", facultyId, e.getMessage(), e);
                throw e;

            } finally {
                // Limpiar el contexto MDC después de la ejecución
                MDC.clear();
            }
        });
    }


    @Transactional
    @Override
    public AcadProgramOutDTO getAcadProgram(long facultyId, long acadProgramId) {
        // Agregar detalles contextuales al MDC
        MDC.put("operation", "getAcadProgram");
        MDC.put("facultyId", String.valueOf(facultyId));
        MDC.put("acadProgramId", String.valueOf(acadProgramId));
        MDC.put("method", "GET");

        logger.info("Starting method | facultyId={}, acadProgramId={}", facultyId, acadProgramId);

        // Métrica de rendimiento
        return meterRegistry.timer("acadProgram.getById").record(() -> {
            try {
                // Validación de acceso
                logger.debug("Validating access | facultyId={}, acadProgramId={}", facultyId, acadProgramId);
                validateAccess(facultyId, acadProgramId, UserPermAccess.QUERY, CURRENT);

                // Búsqueda del programa académico
                logger.debug("Fetching academic program | facultyId={}, acadProgramId={}", facultyId, acadProgramId);
                AcadProgram acadProgram = findAcadProgram(facultyId, acadProgramId);

                // Conversión a DTO
                AcadProgramOutDTO programOutDTO = acadProgramMapper.acadProgramToAcadProgramOutDto(acadProgram);

                logger.info("Successfully retrieved academic program | facultyId={}, acadProgramId={}", facultyId, acadProgramId);
                return programOutDTO;

            } catch (Exception e) {
                logger.error("Error in getAcadProgram | facultyId={}, acadProgramId={}, message={}", facultyId, acadProgramId, e.getMessage(), e);
                throw e;

            } finally {
                // Limpiar el contexto MDC después de la ejecución
                MDC.clear();
            }
        });
    }


    //TODO enable AspectJ for non-injected cache calls, change visibility to non-public
    @Cacheable(key = "#acadProgramId")
    public AcadProgram findAcadProgram(long facultyId, long acadProgramId) {
        // TODO: validate acadProgram is in faculty.
        return acadProgramRepository.findByAcpId(acadProgramId)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.PROGACAD_INVALID_PROGRAM_ID));
    }

    @Transactional
    @Override
    public AcadProgramOutDTO createAcadProgram(long facultyId, AcadProgramInDTO acadProgramInDTO) {
        // Agregar detalles contextuales al MDC
        MDC.put("operation", "createAcadProgram");
        MDC.put("facultyId", String.valueOf(facultyId));
        MDC.put("method", "POST");

        logger.info("Starting method | facultyId={}, acadProgramInDTO={}", facultyId, acadProgramInDTO);

        // Métrica de rendimiento
        try {
            // Validación de acceso
            logger.debug("Validating access for facultyId={}", facultyId);
            validateAccess(facultyId, 0L, UserPermAccess.ADMIN, CURRENT);

            // Búsqueda de facultad
            logger.debug("Fetching faculty | facultyId={}", facultyId);
            Faculty faculty = facultyProvider.findFacultyByFacId(facultyId);

            // Mapeo del DTO de entrada al objeto de entidad
            logger.debug("Mapping AcadProgramInDTO to AcadProgram | facultyId={}, acadProgramInDTO={}", facultyId, acadProgramInDTO);
            AcadProgram acadProgram = acadProgramMapper.acadProgramInDTOToAcadProgram(acadProgramInDTO);
            acadProgram.setFaculty(faculty);

            // Guardar el nuevo programa académico
            logger.debug("Saving new AcadProgram | facultyId={}", facultyId);
            AcadProgramOutDTO createdProgram = acadProgramMapper.acadProgramToAcadProgramOutDto(acadProgramRepository.save(acadProgram));

            // Incrementar contador de métrica
            meterRegistry.counter("acadProgram.created").increment();

            logger.info("Successfully created academic program | facultyId={}, acadProgramOutDTO={}", facultyId, createdProgram);
            return createdProgram;

        } catch (Exception e) {
            logger.error("Error in createAcadProgram | facultyId={}, acadProgramInDTO={}, message={}", facultyId, acadProgramInDTO, e.getMessage(), e);
            throw e;

        } finally {
            // Limpiar el contexto MDC después de la ejecución
            MDC.clear();
        }
    }


    @Transactional
    @Override
    @CachePut(key = "#facultyId")
    public void updateAcadProgram(long facultyId, long acadProgramId, AcadProgramInDTO acadProgramInDTO) {
        //TODO validate the faculty
        // TODO: validate acadProgram is in faculty. Throw exception if program does not exists
        validateAccess(facultyId, acadProgramId, UserPermAccess.ADMIN, CURRENT);
        AcadProgram acadProgram = findAcadProgram(facultyId, acadProgramId);
        acadProgramMapper.updateAcadProgram(acadProgramInDTO, acadProgram);
        acadProgramRepository.save(acadProgram);
    }

    @Transactional
    @Override
    @CacheEvict(key = "#acadProgramId")
    public void deleteAcadProgram(long facultyId, long acadProgramId) {
        //TODO validate the faculty
        // TODO: validate acadProgram is in faculty. Throw exception if program does not exists
        validateAccess(facultyId, acadProgramId, UserPermAccess.ADMIN, CURRENT);
        AcadProgram acadProgram = findAcadProgram(facultyId, acadProgramId);
        acadProgramRepository.delete(acadProgram);
    }

    private void validateAccess(long facultyId, long acadProgId, UserPermAccess permAccess,
            AcadProgramPermType.AcadProgramPermStatus permStatus) {
        acadProgramValidator.enforceUsrFacForAcadProgram(facultyId, permAccess, permStatus);
        acadProgramValidator.enforceUsrPrgForAcadProgram(acadProgId, permAccess, permStatus);
    }

}
