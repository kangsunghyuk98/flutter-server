package com.flutserver01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);    // response js에서 처리 가능
        configuration.addAllowedOrigin("*");        // All IP response allow
        configuration.addAllowedHeader("*");        // All header response allow
        configuration.addAllowedMethod("*");        // All method response allow

        source.registerCorsConfiguration("/api/**", configuration);

        return new CorsFilter(source);
    }
}
