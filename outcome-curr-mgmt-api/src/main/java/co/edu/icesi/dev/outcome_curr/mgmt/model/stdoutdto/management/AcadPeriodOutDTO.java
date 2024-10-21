package co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management;

import lombok.Builder;

@Builder
public record AcadPeriodOutDTO(

        Long acPeriodId,

        String acPeriodNameEng,

        String acPeriodNameSpa,

        int acPeriodNumeric
) {

}


