package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import java.util.HashMap;
import java.util.Map;

public abstract class Cell {
    private Map<String, String> values;

    protected Cell() {
        values = new HashMap<>();
    }

    public Map<String, String> getValues() {
        return values;
    }

    protected void putKeyValueInMap(String key, String value) {
        values.put(key, value);
    }

    public abstract void initializeCellValues();
}
