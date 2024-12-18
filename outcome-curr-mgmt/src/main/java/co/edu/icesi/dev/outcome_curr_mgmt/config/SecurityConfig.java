package co.edu.icesi.dev.outcome_curr_mgmt.config;

import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.filters.SaamfiAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.http.HttpHeaders;
import java.util.Collections;
import java.util.regex.Pattern;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable) // Deshabilitar CORS para pruebas
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll()) 
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            String origin = request.getHeader(HttpHeaders.ORIGIN);
            Pattern pattern = Pattern.compile("https://([A-Za-z0-9-]+)\\.jcmunoz\\.net");
            CorsConfiguration configuration = new CorsConfiguration();

            if (origin == null) {
                return configuration;
            }

            if (!pattern.matcher(origin).matches()){
                return configuration;
            }

            configuration.setAllowedOrigins(Collections.singletonList(origin));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            return configuration;

        };
    }

}
