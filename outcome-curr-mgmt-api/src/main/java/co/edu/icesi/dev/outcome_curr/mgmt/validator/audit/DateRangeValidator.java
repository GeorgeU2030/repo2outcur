package co.edu.icesi.dev.outcome_curr.mgmt.validator.audit;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateRangeValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRangeValidator {
    String message() default "Date Range is not valid";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
