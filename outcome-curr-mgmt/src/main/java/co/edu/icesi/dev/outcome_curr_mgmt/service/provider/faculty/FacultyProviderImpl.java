package co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.FacultyMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.FacultyRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.FacultyValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyProviderImpl implements FacultyProvider{

    private final FacultyRepository facultyRepository;
    private final ChangeLogService changeLogService;
    private final FacultyValidator facultyValidator;
    private final FacultyMapper facultyMapper;
    private static final Logger logger = LoggerFactory.getLogger(FacultyProviderImpl.class);

    @Override
    public FacultyOutDTO saveFaculty(FacultyInDTO facultyInDTO) {
        logger.info("Creating a faculty.");
        validateAccess(0L, UserPermAccess.ADMIN);

        checkIfSpaNameIsAlreadyUsed(facultyInDTO.facNameSpa());
        checkIfEngNameIsAlreadyUsed(facultyInDTO.facNameEng());
        checkIfExternalIdIsAlreadyImported(facultyInDTO.externalId());

        Faculty newFaculty = facultyMapper.facultyInDTOToFaculty(facultyInDTO);
        facultyRepository.save(newFaculty);

        logger.info("Faculty successfully saved.");

        addActionToChangelog(ChangeLogAction.CREATE, newFaculty.getFacId(),"FACULTY", newFaculty, null);

        return facultyMapper.facultyToFacultyOutDTO(newFaculty);
    }
    @Override
    public void addActionToChangelog(ChangeLogAction action, long facId, String affectedTables, Faculty newfaculty,
            Object oldFaculty){
        logger.info("Saving the {} action of a faculty in the changelog", action.name());

        changeLogService.addChange(
                action,
                Long.toString(facId),
                affectedTables,
                newfaculty,
                oldFaculty);

        logger.info("The {} action of a faculty was saved in the changelog", action.name());
    }

    //TODO jcmunoz: enable AspectJ for non-injected cache calls, change visibility to non-public
    @Override
    //@Cacheable(key = "#facultyId")
    public Faculty findFacultyByFacId(long facId) {
        logger.info("Finding a faculty by its id ");
        Optional<Faculty> foundedFaculty = facultyRepository.findById(facId);
        if (foundedFaculty.isEmpty()){
            logger.error("Error: a faculty with the ID {} does not exists.", facId);
            throw new OutCurrException(OutCurrExceptionType.FACULTY_INVALID_FAC_ID);
        }
        logger.info("The founded faculty was returned.");
        return foundedFaculty.get();
    }

    @Override
    public void checkIfEngNameIsAlreadyUsed(String facNameEng){
        logger.info("Verifying if the English name of the new faculty is already in use.");

        if (findFacultyByEngName(facNameEng).isPresent()) {
            logger.error("Error: the English name {} is already in use.", facNameEng);
            throw new OutCurrException(OutCurrExceptionType.FACULTY_DUPLICATED_FAC_NAME_ENG);
        }

        logger.info("The given English name can be used.");
    }

    @Override
    public void checkIfSpaNameIsAlreadyUsed(String facNameSpa) {
        logger.info("Verifying if the Spanish name of the new faculty is already in use.");

        if (findFacultyBySpaName(facNameSpa).isPresent()) {
            logger.error("Error: the Spanish name {} is already in use.", facNameSpa);
            throw new OutCurrException(OutCurrExceptionType.FACULTY_DUPLICATED_FAC_NAME_SPA);
        }

        logger.info("The given Spanish name can be used.");
    }

    @Override
    public void checkIfExternalIdIsAlreadyImported(String externalId) {
        logger.info("Verifying if the external id is already imported.");

        if (findFacultyByExternalId(externalId).isPresent()) {
            logger.error("Error: the faculty is already imported.");
            throw new OutCurrException(OutCurrExceptionType.FACULTY_ALREADY_IMPORTED);
        }

        logger.info("The given faculty can be imported.");

    }

    @Override
    public FacultyOutDTO getFacultyByNameInEng(String name){
        logger.info("Getting a faculty by its name in English: {}.", name);
        validateAccess(0L,UserPermAccess.QUERY);

        Optional<Faculty> foundedFaculty = findFacultyByEngName(name);
        if (foundedFaculty.isEmpty()){
            logger.error("Error: a faculty with the English name {} does not exists.", name);
            throw new OutCurrException(OutCurrExceptionType.FACULTY_INVALID_FAC_NAME_ENG);
        }
        logger.info("The faculty with the English name {} was returned.", name);
        return facultyMapper.facultyToFacultyOutDTO(foundedFaculty.get());
    }

    @Override
    public FacultyOutDTO getFacultyByNameInSpa(String name) {
        logger.info("Getting a faculty by its name in Spanish: {}.", name);
        validateAccess(0L,UserPermAccess.QUERY);
        Optional<Faculty> foundedFaculty = findFacultyBySpaName(name);
        if (foundedFaculty.isEmpty()){
            logger.error("Error: a faculty with the Spanish name {} does not exists.", name);
            throw new OutCurrException(OutCurrExceptionType.FACULTY_INVALID_FAC_NAME_SPA);
        }
        logger.info("The faculty with the Spanish name {} was returned.", name);
        return facultyMapper.facultyToFacultyOutDTO(foundedFaculty.get());
    }


    private Optional<Faculty> findFacultyByEngName(String facNameEng){
        logger.info("Returning a faculty by its name in English.");
        return facultyRepository.findByFacNameEng(facNameEng);
    }

    private Optional<Faculty> findFacultyBySpaName(String facNameSpa){
        logger.info("Returning a faculty by its name in Spanish.");
        return facultyRepository.findByFacNameSpa(facNameSpa);
    }

    private Optional<Faculty> findFacultyByExternalId(String externalId) {
        logger.info("Returning a faculty by its external id.");
        return facultyRepository.findByExternalId(externalId);
    }

    @Override
    public void validateAccess(long facultyId, UserPermAccess permAccess) {
        logger.info("Checking permissions to execute this operation.");
        facultyValidator.enforceUsrFacForFaculty(facultyId, permAccess);
    }
}
