package co.edu.icesi.dev.outcome_curr_mgmt.model.enums;

import io.micrometer.common.util.StringUtils;

public enum ChangeLogAction {

    CREATE("CREATE"),
    READ("READ"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),

    //Curricular Mapping
    SUGGEST("SUGGEST"),
    ACCEPT("ACCEPT");

    private final String key;

    ChangeLogAction(String key) {
        this.key = key;
    }

    public static ChangeLogAction fromString(String key) {
        return StringUtils.isNotBlank(key) ? ChangeLogAction.valueOf(key) : null;
    }

    public String getKey() {
        return key;
    }

}
