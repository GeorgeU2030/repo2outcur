package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.validator.audit.DateRangeValidatorImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DateRangeValidatorImplTest {

    @InjectMocks
    DateRangeValidatorImpl dateRangeValidator;

    @Test
    void testIsValidWithRangeDates(){

       boolean isValid= dateRangeValidator.isValid(OutcomeCurrMgmtUtil.changeLogDateFilterInDTO(),null);
       assertTrue(isValid);
    }

    @Test
    void testIsValidWithUserOnly(){
        boolean isValid= dateRangeValidator.isValid(OutcomeCurrMgmtUtil.changeLogUserFilterInDTO(),null);
        assertTrue(isValid);
    }
    @Test
    void testIsValidWithInvalidRangeDates(){
        boolean isValid= dateRangeValidator.isValid(OutcomeCurrMgmtUtil.changeLogDateFilterInDTOInvalid(),null);
        assertFalse(isValid);
    }

    @Test
    void testIsValidWithInvalidDateFormatOfRangeDates(){
        boolean isValid= dateRangeValidator.isValid(OutcomeCurrMgmtUtil.changeLogDateFilterInDTOBadRequest(),null);
        assertFalse(isValid);
    }
}
