package co.edu.icesi.dev.outcome_curr_mgmt.service.validator;

import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public abstract class BaseValidatorTest {
    @Mock
    protected SaamfiJwtTools saamfiJwtTools;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
