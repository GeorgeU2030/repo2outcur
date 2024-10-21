package co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BannerClientConfigImpl implements BannerClientConfig{
    @Value("${banner.url}")
    private String bannerUrl;

    @Value("${banner.username}")
    private String bannerUser;

    @Value("${banner.password}")
    private String bannerPass;

    @Override
    public String getBannerUrl() {
        return bannerUrl;
    }

    @Override
    public String getBannerUser() {
        return bannerUser;
    }

    @Override
    public String getBannerPass() {
        return bannerPass;
    }
}
