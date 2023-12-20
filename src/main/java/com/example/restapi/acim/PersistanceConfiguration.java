package com.example.restapi.acim;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistanceConfiguration {

    private static final String APP_USER = "SYSTEM";

    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of(APP_USER);
    }

    /**
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                return Optional.of("SYSTEM");
            }
        };
    }
     */
}
