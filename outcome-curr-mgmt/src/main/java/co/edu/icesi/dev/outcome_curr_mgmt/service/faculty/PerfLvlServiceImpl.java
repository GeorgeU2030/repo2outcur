package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.PerfLvlInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.PerfLvlOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.PerfLvlMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PerfLvl;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.PerfLvlRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogServiceImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.AcadProgramProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.AcadProgramValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType.AcadProgramPermStatus.CURRENT;

@Service
@RequiredArgsConstructor
public class PerfLvlServiceImpl implements PerfLvlService {

    private static final Logger logger = LoggerFactory.getLogger(PerfLvlServiceImpl.class);
    private final PerfLvlRepository perfLvlRepository;
    private final PerfLvlMapper perfLvlMapper;
    private final AcadProgramValidator acadProgramValidator;
    private final ChangeLogServiceImpl changeLogService;
    private final AcadProgramProvider acadProgramProvider;

    private static final String PERF_LVL = "PerfLvl";

    @Transactional
    @Override
    public PerfLvlOutDTO addPerfLvl(PerfLvlInDTO perfLvlInDTO, long acadProgId,long facultyId) {

        logger.info("Creating a performance level");

        validateAccess(facultyId, acadProgId, UserPermAccess.ADMIN,CURRENT);
        AcadProgram acadProgram= acadProgramValidator.validatAcadProgOnFaculty(facultyId,acadProgId);
        PerfLvl perfLvl= perfLvlMapper.fromPerfLvlInDTO(perfLvlInDTO);
        verifyPerfLevel(perfLvl,acadProgId);
        perfLvl.setAcadProgram(acadProgram);
        PerfLvlOutDTO perfLvlOutDTO = perfLvlMapper.fromPerfLvl(perfLvlRepository.save(perfLvl));

        logger.info("Performance level created");

        addPerfLvlToChangelog(perfLvlOutDTO.plId(),perfLvlOutDTO,null,ChangeLogAction.CREATE);
        return perfLvlOutDTO;
    }

    @Transactional
    @Override
    public PerfLvlOutDTO updatePerfLvl(PerfLvlInDTO perfLvlDTONew, long acadProgId, long facultyId,long perfLvlId) {
        logger.info("Editing a performance level");
        validateAccess(facultyId, acadProgId, UserPermAccess.ADMIN,CURRENT);
        validateStructure(facultyId,acadProgId,perfLvlId);

        PerfLvl perfLvlOld=perfLvlRepository.findById(perfLvlId).get();
        verifyPerfLevel(perfLvlOld,acadProgId,perfLvlDTONew);

        PerfLvl perfLvlNew= perfLvlMapper.fromPerfLvlInDTO(perfLvlDTONew);

        perfLvlNew.setPlId(perfLvlId);
        perfLvlNew.setAcadProgram(perfLvlOld.getAcadProgram());

        perfLvlRepository.save(perfLvlNew);
        PerfLvlOutDTO perfLvlOutDTONew= perfLvlMapper.fromPerfLvl(perfLvlNew);
        logger.info("Performance level edited");

        addPerfLvlToChangelog(perfLvlId,perfLvlOutDTONew,perfLvlMapper.fromPerfLvl(perfLvlOld),ChangeLogAction.UPDATE);

        return perfLvlOutDTONew;
    }

    @Transactional
    @Override
    public void deletePerfLvl(long acadProgId, long facultyId, long perfLvlId) {
        logger.info("Deleting a performance level");
        validateAccess(facultyId, acadProgId, UserPermAccess.ADMIN,CURRENT);
        validateStructure(facultyId,acadProgId,perfLvlId);

        PerfLvl perfLvl= perfLvlRepository.findById(perfLvlId).get();

        perfLvlRepository.deleteById(perfLvlId);
        logger.info("Performance level deleted");

        addPerfLvlToChangelog(perfLvlId, null, perfLvlMapper.fromPerfLvl(perfLvl),ChangeLogAction.DELETE);

    }

    private void addPerfLvlToChangelog(long plId, PerfLvlOutDTO perfLvlNewVal, PerfLvlOutDTO perfLvlOldVal, ChangeLogAction changeLogAction){
        logger.info("Saving action of Performance Level in the changelog");

        changeLogService.addChange(changeLogAction,
                String.valueOf(plId), PERF_LVL, perfLvlNewVal, perfLvlOldVal);

        logger.info("Action of Performance Level saved in the changelog");
    }



    private void validateAccess(long facultyId, long acadProgId, UserPermAccess permAccess,
            AcadProgramPermType.AcadProgramPermStatus permStatus) {
        acadProgramValidator.enforceUsrFacForAcadProgram(facultyId, permAccess, permStatus);
        acadProgramValidator.enforceUsrPrgForAcadProgram(acadProgId, permAccess, permStatus);
    }
    private void validateStructure(long facultyId, long acadProgId,long perfLvlId){
        acadProgramValidator.validatAcadProgOnFaculty(facultyId,acadProgId);

        if (perfLvlRepository.findByAcadProgramAcpIdAndPlId(acadProgId, perfLvlId).isEmpty()) {
            throw new OutCurrException(OutCurrExceptionType.PERFLVL_INVALID_PL_ID);
        }
    }

    private void validateStructure(long facultyId, long acadProgId){
        acadProgramValidator.validatAcadProgOnFaculty(facultyId,acadProgId);
    }

    private void  verifyPerfLevel(PerfLvl perfLvlOld, long acadProgId, PerfLvlInDTO perfLvlNew){
        if (!perfLvlOld.getPlNameSpa().equals(perfLvlNew.plNameSpa())) {
            verifyPlNameSpa(acadProgId,perfLvlNew.plNameSpa());
        }

        if (!perfLvlOld.getPlNameEng().equals(perfLvlNew.plNameEng())) {
            verifyPlNameEng(acadProgId,perfLvlNew.plNameEng());
        }

    }

    private void  verifyPerfLevel(PerfLvl perfLvl, long acadProgId){
        verifyPlNameSpa(acadProgId,perfLvl.getPlNameSpa());

        verifyPlNameEng(acadProgId,perfLvl.getPlNameEng());
    }

    private void verifyPlNameSpa( long acadProgId, String plNameSpa){
        if(perfLvlRepository.findByAcadProgramAcpIdAndPlNameSpa(acadProgId,plNameSpa).isPresent()){
            throw new OutCurrException(OutCurrExceptionType.PERFLVL_INVALID_PL_NAME_SPA);
        }
    }

    private void verifyPlNameEng( long acadProgId, String plNameEng){
        if(perfLvlRepository.findByAcadProgramAcpIdAndPlNameEng(acadProgId,plNameEng).isPresent() ) {
            throw new OutCurrException(OutCurrExceptionType.PERFLVL_INVALID_PL_NAME_ENG);
        }
    }

    private AcadProgramPermType.AcadProgramPermStatus getAcadProgramPermStatus(long programId) {
        AcadProgram acadProgram = acadProgramProvider.findAcadProgram( programId);
        return acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(acadProgram.getStartAcPeriod(), acadProgram.getEndAcPeriod());
    }

    @Transactional
    @Override
    public PerfLvlOutDTO getPerfLvl(long perfLvlId, long acadProgId, long facultyId) {
        logger.info("Obtaining performance level of an academic program");
        validateAccess(facultyId, acadProgId, UserPermAccess.QUERY,getAcadProgramPermStatus(acadProgId));
        validateStructure(facultyId,acadProgId,perfLvlId);

        PerfLvl perfLvl= perfLvlRepository.findById(perfLvlId).get();
        PerfLvlOutDTO perfLvlOutDTO=perfLvlMapper.fromPerfLvl(perfLvl);

        logger.info("Performance level obtained");

        return perfLvlOutDTO;

    }

    @Transactional
    @Override
    public List<PerfLvlOutDTO> getAllPerfLvls(long acadProgId, long facultyId) {

        logger.info("Obtaining all performance levels of an academic program");
        validateAccess(facultyId, acadProgId, UserPermAccess.QUERY,getAcadProgramPermStatus(acadProgId));
        validateStructure(facultyId,acadProgId);

        List<PerfLvlOutDTO> perfLvls=perfLvlRepository.findAllByAcadProgramAcpId(acadProgId).stream().map(perfLvlMapper::fromPerfLvl).toList();

        logger.info("Performance levels obtained");

        return perfLvls;
    }
}
