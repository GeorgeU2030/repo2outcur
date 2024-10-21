package co.edu.icesi.dev.outcome_curr_mgmt.exception;

import jakarta.annotation.Nonnull;
import lombok.Getter;

@Getter
public class OutCurrException extends RuntimeException {
    private final OutCurrExceptionType outCurrExceptionType;

    public OutCurrException(@Nonnull OutCurrExceptionType outCurrExceptionType) {
        super(outCurrExceptionType.getMessage());
        this.outCurrExceptionType = outCurrExceptionType;
    }
}
