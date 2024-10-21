package co.edu.icesi.dev.outcome_curr_mgmt.service.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.audit.ChangeLogFilterInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.audit.ChangeLogOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.audit.ChangeLogMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.audit.ChangeLogRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UserRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangeLogServiceImpl implements ChangeLogService {

    private final ChangeLogRepository changeLogRepository;
    private final ChangeLogMapper changeLogMapper;

    private final UserRepository userRepository;
    private final SaamfiJwtTools saamfiJwtTools;

    public static final String DATEFORMAT = "dd/MM/yyyy HH:mm";

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Changelog addChange(ChangeLogAction clogAction, String clogAffectedRecordId,
            String clogAffectedTable, Object clogLogNewVal, Object clogLogOldVal) {
        User user = userRepository.findByUsrName(saamfiJwtTools.getLoggedInUserUsername());

        String clogLogNewValStr= entityToString(clogLogNewVal);
        String clogLogOldValStr= entityToString(clogLogOldVal);

        Changelog changelog=Changelog.builder()
                .clogAction(clogAction.toString())
                .clogAffectedRecordId(clogAffectedRecordId)
                .clogAffectedTable(clogAffectedTable)
                .clogLogNewVal(clogLogNewValStr)
                .clogLogOldVal(clogLogOldValStr)
                .clogTimestamp(new Timestamp((new Date()).getTime()))
                .user(user)
                .build();

        return changeLogRepository.save(changelog);
    }

    private String entityToString(Object object) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            if(object==null){
                return null;
            }
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new OutCurrException(OutCurrExceptionType.CLOG_INVALID_VAL);
        }

    }


    @Override
    public List<ChangeLogOutDTO> getAllChanges() {
        return changeLogRepository.findAll().stream().map(changeLogMapper::fromChangeLog).toList();
    }

    @Override
    public List<ChangeLogOutDTO> getAllChangesByFilter(ChangeLogFilterInDTO changeLogFilterInDTO) {
        Date startDate = null;
        Date endDate=null;
        try {
            if (changeLogFilterInDTO.clogStartDate()!=null && changeLogFilterInDTO.clogEndDate()!=null){
                startDate = new SimpleDateFormat(DATEFORMAT).parse(changeLogFilterInDTO.clogStartDate());
                endDate=new SimpleDateFormat(DATEFORMAT).parse(changeLogFilterInDTO.clogEndDate());
            }
        } catch (ParseException e) {
            throw new OutCurrException(OutCurrExceptionType.CLOG_INVALID_DATE_FORMAT);
        }
        return changeLogRepository.findAllByFilter(changeLogFilterInDTO.usrName(),
                changeLogFilterInDTO.entityName(), startDate,endDate).stream().map(changeLogMapper::fromChangeLog).toList();

    }
}
