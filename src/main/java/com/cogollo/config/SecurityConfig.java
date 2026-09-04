package com.cogollo.config;

import com.cogollo.auth.JwtAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/api/auth/**", "/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/propietarios/**").hasAuthority("ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/api/empleados/**").hasAuthority("PROPIETARIO")
                .requestMatchers(HttpMethod.POST, "/api/restaurantes/**").hasAuthority("ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/api/platos/**").hasAuthority("PROPIETARIO")
                .requestMatchers(HttpMethod.PUT, "/api/platos/**").hasAuthority("PROPIETARIO")
                .requestMatchers(HttpMethod.POST, "/api/pedidos/**").hasAuthority("EMPLEADO")
                .anyRequest().authenticated())
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
