package co.edu.icesi.dev.outcome_curr_mgmt.model.response;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@AllArgsConstructor
@Generated
public class OutcomeCurrApplicationError {
    private final String code;
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime time;
}
