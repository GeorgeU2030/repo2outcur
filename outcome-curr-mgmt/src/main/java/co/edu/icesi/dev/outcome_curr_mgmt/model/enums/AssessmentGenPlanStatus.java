package co.edu.icesi.dev.outcome_curr_mgmt.model.enums;

import io.micrometer.common.util.StringUtils;

public enum AssessmentGenPlanStatus implements BaseStatus{
    FUTURE("FUTURE"),
    EXECUTING("EXECUTING"),
    REVIEW("REVIEW"),
    CLOSED("CLOSED");

    private final String key;

    AssessmentGenPlanStatus(String key) {
        this.key = key;
    }

    public static AssessmentGenPlanStatus fromString(String key) {
        return StringUtils.isNotBlank(key) ? AssessmentGenPlanStatus.valueOf(key) : null;
    }

    public String getKey() {
        return key;
    }
}
