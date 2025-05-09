/*
package com.task.Employee.Service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                         auth.requestMatchers("/employee/view/{id}").hasRole("ADMIN");
                         auth.anyRequest().authenticated();
                });
        http
                .csrf(c -> c.disable()
                );
        return http.build();
    }
}*/
