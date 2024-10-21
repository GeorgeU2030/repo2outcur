package co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AcadProgramRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.managment.AcPeriodProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcadProgramProviderTest {


    @Mock
    AcadProgramRepository acadProgramRepository;

    @Mock
    AcPeriodProvider acPeriodProvider;

    @InjectMocks
    AcadProgramProviderImpl acadProgramProvider;



    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(acadProgramProvider, "acadProgramRepository", acadProgramRepository);
        ReflectionTestUtils.setField(acadProgramProvider, "acPeriodProvider", acPeriodProvider);

    }

    @Test
    void testGetAcadProgramPermStatus_CurrentRange() {

       when(acPeriodProvider.getCurrentAcPeriod()).thenReturn(202302);
        AcadProgramPermType.AcadProgramPermStatus  status = acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD(), OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD2());
        assertEquals(AcadProgramPermType.AcadProgramPermStatus .CURRENT, status);

    }

    @Test
    void testGetAcadProgramPermStatus_FutureRange() {
        when(acPeriodProvider.getCurrentAcPeriod()).thenReturn(202301);
        AcadProgramPermType.AcadProgramPermStatus  status = acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD(), OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD2());
        assertEquals(AcadProgramPermType.AcadProgramPermStatus .FUTURE, status);

    }

    @Test
    void testGetAcadProgramPermStatus_InactiveRange() {
        when(acPeriodProvider.getCurrentAcPeriod()).thenReturn(202402);
        AcadProgramPermType.AcadProgramPermStatus  status = acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD(), OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD2());
        assertEquals(AcadProgramPermType.AcadProgramPermStatus .INACTIVE, status);

    }

    @Test
    void testGetAcadProgramPermStatus_StartAcPeriodGreaterThanEndAcPeriod() {
        when(acPeriodProvider.getCurrentAcPeriod()).thenReturn(202302);
        AcadProgramPermType.AcadProgramPermStatus  status = acadProgramProvider.getAcadProgramPermStatusOfAPeriodRange(OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD2(), OutcomeCurrMgmtUtil .DEFAULT_ACPERIOD());
        assertEquals(AcadProgramPermType.AcadProgramPermStatus .INACTIVE, status);

    }

    @Test
    void testFindAcadProgramHappyPath(){
        when(acadProgramRepository.findById(1234L)).thenReturn(
                Optional.ofNullable(OutcomeCurrMgmtUtil.acadProgramSIS()));
        acadProgramProvider.findAcadProgram(1234L);
        verify(acadProgramRepository,times(1)).findById(any());

    }

    @Test
    void testFindAcadProgramNotFound(){
        try {
            acadProgramProvider.findAcadProgram(1234L);
        }catch(OutCurrException e){
            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();
            assertEquals("Academic Program ID not found",outCurrExceptionType.getMessage());
            assertEquals(4111,outCurrExceptionType.getCode());
            assertEquals("acpId",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.NOT_FOUND,outCurrExceptionType.getResponseStatus());

            verify(acadProgramRepository,times(1)).findById(any());

        }

    }
}