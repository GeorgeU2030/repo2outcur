package co.edu.icesi.dev.outcome_curr_mgmt.model.response;

import lombok.Generated;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Generated
public class OutcomeCurrApplicationErrorDetail extends OutcomeCurrApplicationError {
    private final String detail;
}
