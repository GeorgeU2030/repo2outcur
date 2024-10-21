package co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AcadProgramRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.managment.AcPeriodProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AcadProgramProviderImpl implements AcadProgramProvider {

    private static final Logger logger = LoggerFactory.getLogger(AcadProgramProviderImpl.class);
    private final AcPeriodProvider acPeriodProvider;
    private final AcadProgramRepository acadProgramRepository;
    @Override
    public AcadProgram findAcadProgram(long acadProgramId) {
        return acadProgramRepository.findById( acadProgramId)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.PROGACAD_INVALID_PROGRAM_ID));
    }
    @Override
    @Transactional
    public AcadProgramPermType.AcadProgramPermStatus getAcadProgramPermStatusOfAPeriodRange(AcPeriod startAcadPeriod,
            AcPeriod endAcadPeriod) {
        int startAcadPeriodNumeric = startAcadPeriod.getAcPeriodNumeric();
        int endAcadPeriodNumeric = endAcadPeriod.getAcPeriodNumeric();
        int currentAcPeriod= acPeriodProvider.getCurrentAcPeriod();

        if (startAcadPeriodNumeric > endAcadPeriodNumeric){
            logger.info("Start academic period can't be greater than end academic period");

            return AcadProgramPermType.AcadProgramPermStatus.INACTIVE;
        }

        if (currentAcPeriod < startAcadPeriodNumeric){
            logger.info("Given the range of periods, the status of the range is FUTURE");
            return AcadProgramPermType.AcadProgramPermStatus.FUTURE;
        }

        if (currentAcPeriod > endAcadPeriodNumeric){
            logger.info("Given the range of periods, the status of the range is INACTIVE");
            return AcadProgramPermType.AcadProgramPermStatus.INACTIVE;
        }

        logger.info("Given the range of periods, the status of the range is CURRENT");
        return AcadProgramPermType.AcadProgramPermStatus.CURRENT;

    }
}
