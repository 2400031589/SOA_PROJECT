
package com.primehaven.visitservice.config;

import com.primehaven.visitservice.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            .authorizeHttpRequests(auth -> auth

                // Public visit endpoints, if required
                .requestMatchers("/visits/public/**").permitAll()

                // Schedule a visit
                .requestMatchers(HttpMethod.POST, "/visits")
                    .hasAnyRole("CLIENT", "AGENT", "ADMIN")

                // View visits
                .requestMatchers(HttpMethod.GET, "/visits", "/visits/**")
                    .hasAnyRole("CLIENT", "AGENT", "ADMIN")

                // Update a visit
                .requestMatchers(HttpMethod.PUT, "/visits/**")
                    .hasAnyRole("AGENT", "ADMIN")

                // Delete a visit
                .requestMatchers(HttpMethod.DELETE, "/visits/**")
                    .hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
