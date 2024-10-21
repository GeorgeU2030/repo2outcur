package co.edu.icesi.dev.outcome_curr.mgmt.model.banner.auth;

import java.time.Instant;

public record BannerJwtDTO(
        String token,
        String message
) {
}
