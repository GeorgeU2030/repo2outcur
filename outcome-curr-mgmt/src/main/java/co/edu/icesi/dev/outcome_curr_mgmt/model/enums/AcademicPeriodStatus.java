package co.edu.icesi.dev.outcome_curr_mgmt.model.enums;

public enum AcademicPeriodStatus {
    FUTURE("FUTURE"),
    CURRENT("CURRENT"),
    INACTIVE("INACTIVE");

    private final String key;

    AcademicPeriodStatus(String key) {
        this.key = key;
    }

    public static AcademicPeriodStatus fromString(String key) {
        return key != null ? AcademicPeriodStatus.valueOf(key) : null;
    }

    public String getKey() {
        return key;
    }
}
