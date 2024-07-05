package com.fyp.hca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // Enable method-level security
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Autowired
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/login").permitAll() // Allow unauthenticated access to the login endpoint
                        .requestMatchers("/user/add").hasRole("Super Administrator")
                        .requestMatchers("/user/get").hasAnyRole("Super Administrator", "Province Administrator", "Division Administrator", "District Administrator", "Tehsil Administrator", "Hospital Administrator")
                        .requestMatchers("/user/get/**").hasAnyRole("Super Administrator", "Province Administrator", "Division Administrator", "District Administrator", "Tehsil Administrator", "Hospital Administrator")
                        .requestMatchers("/user/delete/**").hasRole("Super Administrator")
                        .requestMatchers("/user/update").hasRole("Super Administrator")
                        .requestMatchers("/user/getTableData").hasAnyRole("Super Administrator", "Province Administrator", "Division Administrator", "District Administrator", "Tehsil Administrator", "Hospital Administrator")
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)) // Configure JWT authentication converter
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
        // You can configure JwtAuthenticationConverter here if needed
    }
}
