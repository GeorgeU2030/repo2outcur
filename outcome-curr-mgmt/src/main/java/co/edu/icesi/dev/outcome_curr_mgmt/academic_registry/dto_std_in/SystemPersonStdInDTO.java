package co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.dto_std_in;

import lombok.Builder;

@Builder
public record SystemPersonStdInDTO(long userId, String userPhone, String userEmail, String userDocumentId,
                                   String persIsactive, String userLastname) {

}