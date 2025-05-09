package com.task.test.Jwt.security;

import com.task.test.Jwt.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {           //Configure all the Http security of our app.

    private final AuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {    //created to use Authentication filter
        http
                .authorizeHttpRequests(authRequest -> {
                    authRequest.requestMatchers("/auth/authenticate").permitAll();
                    authRequest.requestMatchers("/login").permitAll();
//                    authRequest.anyRequest().authenticated();
                });
        http
                .authenticationProvider(authenticationProvider);
        http
                .csrf(
                csrf->csrf.disable()
        );
        return http.build();
    }
}
