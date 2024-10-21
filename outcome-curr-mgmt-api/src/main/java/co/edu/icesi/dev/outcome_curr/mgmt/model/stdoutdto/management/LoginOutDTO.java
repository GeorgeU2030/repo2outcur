package co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management;

import lombok.Builder;

@Builder
public record LoginOutDTO(
        long userId,
        String userUsername,
        String userExtId,
        String userEmail,
        String userPhone,
        String userName,
        String userLastname,
        String userDocumentId,
        String accessToken,
        String tokenType,
        String systemHomePage

) {
}
