package co.edu.icesi.dev.outcome_curr.mgmt.validator.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.audit.ChangeLogFilterInDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRangeValidatorImpl implements ConstraintValidator<DateRangeValidator, ChangeLogFilterInDTO> {

    @Override
    public void initialize(DateRangeValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ChangeLogFilterInDTO changeLogDateFilterInDTO,
            ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (changeLogDateFilterInDTO.clogStartDate()==null && changeLogDateFilterInDTO.clogEndDate()==null){
                return true;
            }

            Date startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(changeLogDateFilterInDTO.clogStartDate());
            Date endDate=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(changeLogDateFilterInDTO.clogEndDate());
            return startDate.compareTo(endDate)<0;

        } catch (ParseException e) {
            return false;
        }


    }
}
