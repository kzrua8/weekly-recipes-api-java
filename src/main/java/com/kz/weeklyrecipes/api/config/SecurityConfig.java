package com.kz.weeklyrecipes.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // optional, for @PreAuthorize later
public class SecurityConfig {

  @Bean
  SecurityFilterChain security(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable()) // REST/JSON APIs (no browser forms); revisit if you add sessions
      .cors(cors -> {})             // enable if you’ll call from a web frontend
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/actuator/health").permitAll()
        .requestMatchers("/api/**").permitAll()     // open during Sprint 1
        .anyRequest().denyAll()
      );
    return http.build();
  }
}