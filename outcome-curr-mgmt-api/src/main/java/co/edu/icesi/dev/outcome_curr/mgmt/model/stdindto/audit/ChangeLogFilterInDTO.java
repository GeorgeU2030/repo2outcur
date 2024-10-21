package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.validator.audit.DateRangeValidator;
import lombok.Builder;

@Builder
@DateRangeValidator
public record ChangeLogFilterInDTO(

        String usrName,
        String entityName,
        String clogStartDate,
        String clogEndDate

) {

}
