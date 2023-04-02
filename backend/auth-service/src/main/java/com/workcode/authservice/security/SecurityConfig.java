package com.workcode.authservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().anyRequest().permitAll();
        // http
        //     .authorizeHttpRequests((authz) -> authz
        //         .anyRequest().authenticated()
        //     )
        //     .httpBasic(withDefaults());
        return http.build();
    }
}
