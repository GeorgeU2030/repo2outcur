package co.edu.icesi.dev.outcome_curr_mgmt.service.audit;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.audit.ChangeLogMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.audit.ChangeLogRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UserRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ChangeLogServiceTest {

    @InjectMocks
    ChangeLogServiceImpl changeLogService;

    @Mock
    ChangeLogRepository changeLogRepository;

    ChangeLogMapper changeLogMapper= Mappers.getMapper(ChangeLogMapper.class);

    @Mock
    UserRepository userRepository;

    @Mock
    SaamfiJwtTools saamfiJwtTools;



    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(changeLogService,"changeLogRepository",changeLogRepository);
        ReflectionTestUtils.setField(changeLogService,"changeLogMapper",changeLogMapper);
        ReflectionTestUtils.setField(changeLogService,"userRepository",userRepository);
        ReflectionTestUtils.setField(changeLogService,"saamfiJwtTools",saamfiJwtTools);


    }

    @Test
    void testAddChange(){
        var change=OutcomeCurrMgmtUtil.changePerfLvl();
        when(userRepository.findByUsrName(any())).thenReturn(OutcomeCurrMgmtUtil.testUser());
        when(saamfiJwtTools.getLoggedInUserUsername()).thenReturn(OutcomeCurrMgmtUtil.USRNAME);

        Changelog changelog= changeLogService.addChange(
                ChangeLogAction.CREATE,
                "1234",
                "PerfLvl",
                OutcomeCurrMgmtUtil.perfLvl1OutDTO(),
                null
        );
        verify(changeLogRepository,times(1)).save(argThat(new ChangeLogMatcher(change)));

    }

    @Test
    void testAddChangeJsonProcessingException(){
        when(userRepository.findByUsrName(any())).thenReturn(OutcomeCurrMgmtUtil.testUser());
        when(saamfiJwtTools.getLoggedInUserUsername()).thenReturn(OutcomeCurrMgmtUtil.USRNAME);

        try {
             changeLogService.addChange(
                    ChangeLogAction.CREATE,
                    "1234",
                    "PerfLvl",
                    new Object(),
                    null
            );
            fail();
        }catch (OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("ChangeLog new value or old value is not valid",outCurrExceptionType.getMessage());
            assertEquals(4151,outCurrExceptionType.getCode());
            assertEquals("clogLogNewVal or clogLogOldValStr",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,outCurrExceptionType.getResponseStatus());

            verify(changeLogRepository,times(0)).save(any());

        }

    }


    @Test
    void testGetAllChangesByFilterUser(){
        List<Changelog> changelogs= new ArrayList<>();
        changelogs.add(OutcomeCurrMgmtUtil.changePerfLvl());
        when(changeLogRepository.findAllByFilter(any(),any(),any(),any())).thenReturn(changelogs);
        int listSize= changeLogService.getAllChangesByFilter(OutcomeCurrMgmtUtil.changeLogUserFilterInDTO()).size();
        verify(changeLogRepository,times(1)).findAllByFilter(any(),any(),any(),any());
        assertEquals(1,listSize);
    }
    @Test
    void testGetAllChangesByFilterEntity(){
        List<Changelog> changelogs= new ArrayList<>();
        changelogs.add(OutcomeCurrMgmtUtil.changePerfLvl());
        when(changeLogRepository.findAllByFilter(any(),any(),any(),any())).thenReturn(changelogs);
        int listSize= changeLogService.getAllChangesByFilter(OutcomeCurrMgmtUtil.changeLogEntityFilterInDTO()).size();
        verify(changeLogRepository,times(1)).findAllByFilter(any(),any(),any(),any());
        assertEquals(1,listSize);

    }
    @Test
    void testGetAllChanges(){
        List<Changelog> changelogs= new ArrayList<>();
        changelogs.add(OutcomeCurrMgmtUtil.changePerfLvl());
        when(changeLogRepository.findAll()).thenReturn(changelogs);
        int listSize= changeLogService.getAllChanges().size();
        verify(changeLogRepository,times(1)).findAll();
        assertEquals(1,listSize);

    }
    @Test
    void testGetAllChangesByFilterDate(){
        List<Changelog> changelogs= new ArrayList<>();
        changelogs.add(OutcomeCurrMgmtUtil.changePerfLvl());

        try {
            Date startDate=new SimpleDateFormat(ChangeLogServiceImpl.DATEFORMAT).parse("01/08/2022 00:00");
            Date endDate=new SimpleDateFormat(ChangeLogServiceImpl.DATEFORMAT).parse("10/08/2022 23:59");
            when(changeLogRepository.findAllByFilter(any(),any(),eq(startDate),eq(endDate))).thenReturn(changelogs);
            int listSize= changeLogService.getAllChangesByFilter(OutcomeCurrMgmtUtil.changeLogDateFilterInDTO()).size();
            verify(changeLogRepository,times(1)).findAllByFilter(any(),any(),any(),any());
            assertEquals(1,listSize);
        } catch (ParseException e) {
            throw new RuntimeException(e);

        }

    }

    @Test
    void testGetAllChangesByFilterDateParseException(){

        try {
            changeLogService.getAllChangesByFilter(OutcomeCurrMgmtUtil.changeLogDateFilterInDTOBadRequest());
            fail();
        } catch (OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Date in filter for changeLog has invalid format",outCurrExceptionType.getMessage());
            assertEquals(4152,outCurrExceptionType.getCode());
            assertEquals("clogStartDate or clogEndDate",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.BAD_REQUEST,outCurrExceptionType.getResponseStatus());

            verify(changeLogRepository,times(0)).findAllByFilter(any(),any(),any(),any());
        }

    }

}
