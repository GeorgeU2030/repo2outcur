package co.edu.icesi.dev.outcome_curr_mgmt.service.audit;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class ChangeLogMatcher implements ArgumentMatcher<Changelog> {

    private Changelog changelogLeft;

    public ChangeLogMatcher(Changelog changelogLeft){
        this.changelogLeft=changelogLeft;
    }

    @Override
    public boolean matches(Changelog changelogRight) {
        return
                Objects.equals(changelogRight.getClogAction(),changelogLeft.getClogAction()) &&
                Objects.equals(changelogRight.getClogAffectedRecordId(),changelogLeft.getClogAffectedRecordId()) &&
                Objects.equals(changelogRight.getClogAffectedTable(),changelogLeft.getClogAffectedTable()) &&
                Objects.equals(changelogRight.getClogLogNewVal(),changelogLeft.getClogLogNewVal()) &&
                Objects.equals(changelogRight.getClogLogOldVal(),changelogLeft.getClogLogOldVal()) &&
                changelogRight.getClogTimestamp()!=null;

    }
}
