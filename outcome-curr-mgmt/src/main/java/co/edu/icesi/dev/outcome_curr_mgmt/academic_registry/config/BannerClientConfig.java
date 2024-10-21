package co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public interface BannerClientConfig {
    String getBannerUrl();
    String getBannerUser();
    String getBannerPass();
}
