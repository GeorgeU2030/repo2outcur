package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.management.AcademicPeriodMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.AcPeriodRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcPeriodServiceImpl implements AcPeriodService {

    private static final Logger logger = LoggerFactory.getLogger(AcPeriodServiceImpl.class);

    private final AcPeriodRepository acPeriodRepository;

    private final AcademicPeriodMapper academicPeriodMapper;

    private final ChangeLogServiceImpl changeLogService;
    
    private static final String ACPERIOD = "AcPeriod";

    @Transactional
    @Override
    public AcadPeriodOutDTO addAcademicPeriod(AcadPeriodInDTO academicPeriodToCreate) {
        logger.info("Creating a academic period {}",academicPeriodToCreate.acPeriodNumeric());

        verifyAcadPeriodSpaName(academicPeriodToCreate.acPeriodNameSpa());
        verifyAcadPeriodNumber(academicPeriodToCreate.acPeriodNumeric());

        AcPeriod acPeriodToCreate = academicPeriodMapper.fromAcadPeriodInDTO(academicPeriodToCreate);

        AcadPeriodOutDTO acadPeriodCreated = academicPeriodMapper.fromAcadPeriod(
                acPeriodRepository.save(acPeriodToCreate));

        logger.info("Academic period {} created", acPeriodToCreate.getAcPeriodNumeric());
        addActionToChangelog(acPeriodToCreate.getAcPeriodId(), acadPeriodCreated, ChangeLogAction.CREATE, null);

        return acadPeriodCreated;
    }

    private void addActionToChangelog(long acadPeriodId, AcadPeriodOutDTO newAcadPeriodOutDTO, ChangeLogAction action,
            AcadPeriodOutDTO oldValue){

        logger.info("Saving action of Academic Period in the changelog");

        changeLogService.addChange(action, String.valueOf(acadPeriodId), ACPERIOD,
                newAcadPeriodOutDTO, oldValue);

        logger.info("Action saved in the changelog");
    }

    @Transactional
    @Override
    public AcadPeriodOutDTO searchAcademicPeriod(Long acadPeriodId) {
        logger.info("Searching a academic period with id: {}", acadPeriodId);
        verifyIdField(acadPeriodId);

        return verifyAcadPeriod(acadPeriodId);
    }

    private void verifyIdField(Long acadPeriodId){

        logger.info("Verifying academic period id");

        if (acadPeriodId == null){
            logger.error("Academic period id can't be null");
            throw new OutCurrException(OutCurrExceptionType.ACADPERIOD_INVALID_ACPERIOD_ID);
        }
        logger.info("Academic period id valid");
    }

    private AcadPeriodOutDTO verifyAcadPeriod(Long acadPeriodId) {

        Optional<AcPeriod> acPeriod = acPeriodRepository.findById(acadPeriodId);

        if (acPeriod.isEmpty()){
            logger.error("Academic period with id: {} not found", acadPeriodId);
            throw new OutCurrException(OutCurrExceptionType.ACADPERIOD_NOTFOUND_ACPERIOD_ID);
        }

        logger.info("Academic period with id: {} found", acadPeriodId);
        return academicPeriodMapper.fromAcadPeriod(acPeriod.get());
    }

    private void verifyAcadPeriodNumber(int acadPeriodNumber) {

        if (acPeriodRepository.findByAcPeriodNumeric(acadPeriodNumber).isPresent()){
            logger.error("Academic period with number: {} duplicate", acadPeriodNumber);
            throw new OutCurrException(OutCurrExceptionType.ACADPERIOD_INVALID_DUP_ACPERIOD_NUMERIC);
        }
    }

    private void verifyAcadPeriodSpaName(String acadPeriodSpaName) {

        if (acPeriodRepository.findByAcPeriodNameSpa(acadPeriodSpaName).isPresent()){
            logger.error("Academic period with spanish name: {} duplicate",acadPeriodSpaName);
            throw new OutCurrException(OutCurrExceptionType.ACADPERIOD_INVALID_DUP_ACPERIOD_NAME_SPA);
        }
    }

    @Transactional
    @Override
    public AcadPeriodOutDTO searchAcademicPeriodByNumber(int academicPeriodNumber) {
        logger.info("Searching academic period {}", academicPeriodNumber);
        Optional<AcPeriod> acPeriod =  acPeriodRepository.findByAcPeriodNumeric(academicPeriodNumber);

        if (acPeriod.isEmpty()){
            logger.error("Academic period {} not found", academicPeriodNumber);
            throw new OutCurrException(OutCurrExceptionType.ACADPERIOD_NOTFOUND_ACPERIOD_NUMERIC);
        }

        logger.info("Academic period {} found", academicPeriodNumber);
        return academicPeriodMapper.fromAcadPeriod(acPeriod.get());
    }

    @Transactional
    @Override
    public AcadPeriodOutDTO searchAcademicPeriodBySpaName(String academicPeriodSpaName) {

        logger.info("Searching academic period with spanish name: {}", academicPeriodSpaName);
        Optional<AcPeriod> acPeriod =  acPeriodRepository.findByAcPeriodNameSpa(academicPeriodSpaName);

        if (acPeriod.isEmpty()){
            logger.error("Academic period with spanish name: {} not found", academicPeriodSpaName);
            throw new OutCurrException(OutCurrExceptionType.ACADPERIOD_NOTFOUND_ACPERIOD_NAME_SPA);
        }

        logger.info("Academic period with spanish name: {} found", academicPeriodSpaName);
        return academicPeriodMapper.fromAcadPeriod(acPeriod.get());
    }

    @Transactional
    @Override
    public List<AcadPeriodOutDTO> getAllAcademicPeriods() {
        logger.info("Obtaining all academic periods");

        List<AcadPeriodOutDTO> acadPeriods = acPeriodRepository.findAll().stream()
                .map(academicPeriodMapper::fromAcadPeriod).toList();

        logger.info("Academic periods obtained");
        return acadPeriods;
    }

    @Transactional
    @Override
    public AcadPeriodOutDTO setAcademicPeriod(Long acadPeriodId, AcadPeriodInDTO newAcademicPeriod) {
        logger.info("Updating academic period with id {}", (acadPeriodId));

        AcadPeriodOutDTO oldAcadPeriod = searchAcademicPeriod(acadPeriodId);

        validateNewSpaName(newAcademicPeriod, oldAcadPeriod);
        validateNewNumeric(newAcademicPeriod, oldAcadPeriod);

        AcPeriod newAcadPeriod = academicPeriodMapper.fromAcadPeriodInDTO(newAcademicPeriod);
        newAcadPeriod.setAcPeriodId(oldAcadPeriod.acPeriodId());

        acPeriodRepository.save(newAcadPeriod);
        logger.info("Academic period with id: {} updated", acadPeriodId);
        addActionToChangelog(newAcadPeriod.getAcPeriodId(), academicPeriodMapper.fromAcadPeriod(newAcadPeriod),
                ChangeLogAction.UPDATE, oldAcadPeriod);

        return academicPeriodMapper.fromAcadPeriod(newAcadPeriod);
    }

    private void validateNewSpaName(AcadPeriodInDTO newAcademicPeriod, AcadPeriodOutDTO oldAcadPeriod){
        if (!oldAcadPeriod.acPeriodNameSpa().equals(newAcademicPeriod.acPeriodNameSpa())){
            verifyAcadPeriodSpaName(newAcademicPeriod.acPeriodNameSpa());
        }
    }

    private void validateNewNumeric(AcadPeriodInDTO newAcademicPeriod, AcadPeriodOutDTO oldAcadPeriod){
        if (oldAcadPeriod.acPeriodNumeric() != newAcademicPeriod.acPeriodNumeric()){
            verifyAcadPeriodNumber(newAcademicPeriod.acPeriodNumeric());
        }
    }

    @Transactional
    @Override
    public void deleteAcademicPeriod(Long acadPeriodId) {
        logger.info("Deleting a academic period with id {}", acadPeriodId);

        AcadPeriodOutDTO acadPeriodToDelete = searchAcademicPeriod(acadPeriodId);
        acPeriodRepository.deleteById(acadPeriodId);

        logger.info("Academic period with id: {} deleted", acadPeriodId);
        addActionToChangelog(acadPeriodId, null, ChangeLogAction.DELETE, acadPeriodToDelete);
    }
}
