package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.PerfLvlMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PerfLvl;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.PerfLvlRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogServiceImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.AcadProgramProviderImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.AcadProgramValidatorImpl;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerfLvlServiceTest {

    @InjectMocks
     PerfLvlServiceImpl perfLvlService;
    @Mock
     PerfLvlRepository perfLvlRepository;
    PerfLvlMapper perfLvlMapper= Mappers.getMapper(PerfLvlMapper.class);

    @Mock
    AcadProgramValidatorImpl acadProgramValidator;

    @Mock
    AcadProgramProviderImpl acadProgramProvider;

    @Mock
    ChangeLogServiceImpl changeLogService;

    @BeforeEach
     void setup(){
        ReflectionTestUtils.setField(perfLvlService,"perfLvlRepository",perfLvlRepository);
        ReflectionTestUtils.setField(perfLvlService,"acadProgramValidator",acadProgramValidator);
        ReflectionTestUtils.setField(perfLvlService,"perfLvlMapper",perfLvlMapper);
        ReflectionTestUtils.setField(perfLvlService,"changeLogService",changeLogService);
        ReflectionTestUtils.setField(perfLvlService,"acadProgramProvider",acadProgramProvider);

    }

    @Test
    void testCreatePerfLvlHappyPath(){
        var perfLvl= OutcomeCurrMgmtUtil.perfLvl1();
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameSpa(eq(12345L),any())).thenReturn(Optional.empty());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameEng(eq(12345L),any())).thenReturn(Optional.empty());
        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());
        when(perfLvlRepository.save(any())).thenReturn(perfLvl);

        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        perfLvlService.addPerfLvl(OutcomeCurrMgmtUtil.perfLvl1InDTO(),12345L,321L);

        verify(perfLvlRepository,times(1)).save(argThat(new PerfLvlMatcher(perfLvl)));
        verify(changeLogService, times(1)).addChange(any(), any(), any(), any(), any());
    }

    @Test
    void testCreatePerfLvlWhenNameSpaConflict(){
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameSpa(eq(12345L),any())).thenReturn(Optional.ofNullable(
                OutcomeCurrMgmtUtil.perfLvl1()));

        try {
            perfLvlService.addPerfLvl(OutcomeCurrMgmtUtil.perfLvl1InDTO(), 12345L,321L);
            fail();
        }catch(OutCurrException e){
           OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
           assertEquals("Performance Level name (Spanish) already exists for this academic program",outCurrExceptionType.getMessage());
           assertEquals(4149,outCurrExceptionType.getCode());
           assertEquals("plNameSpa",outCurrExceptionType.getParameterName());
           assertEquals(HttpStatus.CONFLICT,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(0)).save(any());
            verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());

        }

    }

    @Test
    void testCreatePerfLvlWhenNameEngConflict(){
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameSpa(eq(12345L),any())).thenReturn(Optional.empty());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameEng(eq(12345L),any())).thenReturn(Optional.ofNullable(
                OutcomeCurrMgmtUtil.perfLvl1()));

        try {
            perfLvlService.addPerfLvl(OutcomeCurrMgmtUtil.perfLvl1InDTO(), 12345L,321L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Performance Level name (English) already exists for this academic program",outCurrExceptionType.getMessage());
            assertEquals(4148,outCurrExceptionType.getCode());
            assertEquals("plNameEng",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(0)).save(any());
            verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());

        }

    }

    @Test
    void testCreatePerfLvlWhenAcadProgramNotFound(){
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenThrow(new OutCurrException(OutCurrExceptionType.PROGACAD_CONFLICT_PROGRAM_ID));
        try {
            perfLvlService.addPerfLvl(OutcomeCurrMgmtUtil.perfLvl1InDTO(), 12345L,321L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Academic Program does not belong to the faculty",outCurrExceptionType.getMessage());
            assertEquals(4119,outCurrExceptionType.getCode());
            assertEquals("acpId",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(0)).save(any());
            verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());
        }
    }

    @Test
    void testGetPerfLvlHappyPath(){

        when(perfLvlRepository.findById(any())).thenReturn(Optional.ofNullable(OutcomeCurrMgmtUtil.perfLvl1()));
        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1L)).thenReturn(Optional.ofNullable(OutcomeCurrMgmtUtil.perfLvl1()));
        when(acadProgramProvider.findAcadProgram(12345L)).thenReturn(OutcomeCurrMgmtUtil.acadProgramSIS());
        when(acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(any(),any())).thenReturn(
                AcadProgramPermType.AcadProgramPermStatus.CURRENT);
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        perfLvlService.getPerfLvl(1L,12345L,321L);

        verify(perfLvlRepository,times(1)).findById(any());
    }

    @Test
    void testGetPerfLvlWhenIdNotFound(){

        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1L)).thenReturn(Optional.empty());
        when(acadProgramProvider.findAcadProgram(12345L)).thenReturn(OutcomeCurrMgmtUtil.acadProgramSIS());
        when(acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(any(),any())).thenReturn(
                AcadProgramPermType.AcadProgramPermStatus.CURRENT);
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        try {
            perfLvlService.getPerfLvl(1L, 12345L,321L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Performance Level ID not found in the academic program",outCurrExceptionType.getMessage());
            assertEquals(4150,outCurrExceptionType.getCode());
            assertEquals("plId",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.NOT_FOUND,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(1)).findByAcadProgramAcpIdAndPlId(12345L,1L);
        }

    }

    @Test
    void testGetAllPerfLvlsHappyPath(){
        List<PerfLvl> perfLvls= new ArrayList<>();
        perfLvls.add( OutcomeCurrMgmtUtil.perfLvl1());
        when(perfLvlRepository.findAllByAcadProgramAcpId(12345L)).thenReturn(perfLvls);
        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());
        when(acadProgramProvider.findAcadProgram(12345L)).thenReturn(OutcomeCurrMgmtUtil.acadProgramSIS());
        when(acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(any(),any())).thenReturn(
                AcadProgramPermType.AcadProgramPermStatus.CURRENT);
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        int listSize= perfLvlService.getAllPerfLvls(12345L,321L).size();


        verify(perfLvlRepository,times(1)).findAllByAcadProgramAcpId(12345L);
        assertEquals(1,listSize);

    }


    @Test
    void testUpdatePerfLvlHappyPath(){
        var perfLvl= OutcomeCurrMgmtUtil.perfLvl1();
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameSpa(eq(12345L),any())).thenReturn(Optional.empty());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameEng(eq(12345L),any())).thenReturn(Optional.empty());
        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1234L)).thenReturn(Optional.ofNullable(perfLvl));
        when(perfLvlRepository.findById(1234L)).thenReturn(Optional.ofNullable(perfLvl));

        when(perfLvlRepository.save(any())).thenReturn(perfLvl);

        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        perfLvlService.updatePerfLvl(OutcomeCurrMgmtUtil.perfLvl2InDTO(),12345L,321L,1234L);

        verify(perfLvlRepository,times(1)).save(argThat(new PerfLvlMatcher(OutcomeCurrMgmtUtil.perfLvl2())));
        verify(changeLogService, times(1)).addChange(any(), any(), any(), any(), any());
    }

    @Test
    void testUpdatePerfLvlWhenNameSpaConflict(){
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1234L)).thenReturn(Optional.ofNullable( OutcomeCurrMgmtUtil.perfLvl1()));

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameSpa(eq(12345L),any())).thenReturn(Optional.ofNullable(
                OutcomeCurrMgmtUtil.perfLvl1()));
        when(perfLvlRepository.findById(1234L)).thenReturn(Optional.ofNullable( OutcomeCurrMgmtUtil.perfLvl1()));
        try {
            perfLvlService.updatePerfLvl(OutcomeCurrMgmtUtil.perfLvl2InDTO(), 12345L,321L,1234L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Performance Level name (Spanish) already exists for this academic program",outCurrExceptionType.getMessage());
            assertEquals(4149,outCurrExceptionType.getCode());
            assertEquals("plNameSpa",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(0)).save(any());
            verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());

        }

    }

    @Test
    void testUpdatePerfLvlWhenNameEngConflict(){
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1234L)).thenReturn(Optional.ofNullable( OutcomeCurrMgmtUtil.perfLvl1()));

        when(perfLvlRepository.findById(1234L)).thenReturn(Optional.ofNullable( OutcomeCurrMgmtUtil.perfLvl1()));

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameSpa(eq(12345L),any())).thenReturn(Optional.empty());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlNameEng(eq(12345L),any())).thenReturn(Optional.ofNullable(
                OutcomeCurrMgmtUtil.perfLvl1()));

        try {
            perfLvlService.updatePerfLvl(OutcomeCurrMgmtUtil.perfLvl2InDTO(), 12345L,321L,1234L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Performance Level name (English) already exists for this academic program",outCurrExceptionType.getMessage());
            assertEquals(4148,outCurrExceptionType.getCode());
            assertEquals("plNameEng",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(0)).save(any());
            verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());

        }

    }

    @Test
    void testUpdatePerfLvlWhenAcadProgramNotFoundInFaculty(){
        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenThrow(new OutCurrException(OutCurrExceptionType.PROGACAD_CONFLICT_PROGRAM_ID));
        try {
            perfLvlService.updatePerfLvl(OutcomeCurrMgmtUtil.perfLvl2InDTO(), 12345L,321L,1234L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Academic Program does not belong to the faculty",outCurrExceptionType.getMessage());
            assertEquals(4119,outCurrExceptionType.getCode());
            assertEquals("acpId",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(0)).save(any());
            verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());
        }
    }

    @Test
    void testUpdatePerfLvlWhenPlNotFoundInAcadProgram(){

        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());
        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1234L)).thenReturn(Optional.empty());

        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        try {
            perfLvlService.updatePerfLvl(OutcomeCurrMgmtUtil.perfLvl2InDTO(), 12345L,321L,1234L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Performance Level ID not found in the academic program",outCurrExceptionType.getMessage());
            assertEquals(4150,outCurrExceptionType.getCode());
            assertEquals("plId",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.NOT_FOUND,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(1)).findByAcadProgramAcpIdAndPlId(12345L,1234L);
            verify(perfLvlRepository,times(0)).save(any());
            verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());
        }

    }


    @Test
    void testDeletePerfLvlHappyPath(){
        var perfLvl= OutcomeCurrMgmtUtil.perfLvl1();
        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1234L)).thenReturn(Optional.ofNullable(perfLvl));
        when(perfLvlRepository.findById(1234L)).thenReturn(Optional.ofNullable(perfLvl));

        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());

        perfLvlService.deletePerfLvl(12345L,321L,1234L);

        verify(perfLvlRepository,times(1)).deleteById(1234L);
        verify(changeLogService, times(1)).addChange(any(), any(), any(), any(), any());
    }

    @Test
    void testDeletePerfLvlWhenPerfLvlNotFound(){
        var perfLvl= OutcomeCurrMgmtUtil.perfLvl1();
        when(acadProgramValidator.validatAcadProgOnFaculty(321L,12345L)).thenReturn(
                OutcomeCurrMgmtUtil.acadProgramSIS());

        when(perfLvlRepository.findByAcadProgramAcpIdAndPlId(12345L,1234L)).thenReturn(Optional.empty());

        doNothing().when(acadProgramValidator).enforceUsrFacForAcadProgram(anyLong(),any(),any());
        doNothing().when(acadProgramValidator).enforceUsrPrgForAcadProgram(anyLong(),any(),any());
        try {
            perfLvlService.deletePerfLvl(12345L, 321L, 1234L);
            fail();
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Performance Level ID not found in the academic program",outCurrExceptionType.getMessage());
            assertEquals(4150,outCurrExceptionType.getCode());
            assertEquals("plId",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.NOT_FOUND,outCurrExceptionType.getResponseStatus());

            verify(perfLvlRepository,times(1)).findByAcadProgramAcpIdAndPlId(12345L,1234L);
            verify(perfLvlRepository,times(0)).deleteById(any());
            verify(changeLogService, times(0)).addChange(any(), any(), any(), any(), any());

        }
     }





}
