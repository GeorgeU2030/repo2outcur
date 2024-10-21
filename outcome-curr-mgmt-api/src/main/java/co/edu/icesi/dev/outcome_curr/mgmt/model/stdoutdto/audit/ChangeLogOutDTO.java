package co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.audit;

public record ChangeLogOutDTO(

        long clogId,
        String clogAction,
        String clogAffectedRecordId,
        String clogAffectedTable,
        String clogLogNewVal,
        String clogLogOldVal,
        String clogTimestamp,
        String user
) {
}
