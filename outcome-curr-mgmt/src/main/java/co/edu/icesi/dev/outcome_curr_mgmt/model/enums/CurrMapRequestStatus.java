package co.edu.icesi.dev.outcome_curr_mgmt.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum CurrMapRequestStatus {
    APPROVED("APPROVED"),
    PENDING("PENDING"),
    REJECTED("REJECTED"),
    REPLACED("REPLACED");

    private final String key;
}
