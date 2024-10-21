package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AcadPeriodInDTO (

        @NotBlank(message = "English Name may not be empty")
        String acPeriodNameEng,

        @NotBlank(message = "Spanish Name may not be empty")
        String acPeriodNameSpa,

        @Min(value = 200000, message = "Min value is an academic period greater than 199999")
        @Max(value = 209999, message = "Max value is an academic period less than 300000")
        int acPeriodNumeric
){

}


