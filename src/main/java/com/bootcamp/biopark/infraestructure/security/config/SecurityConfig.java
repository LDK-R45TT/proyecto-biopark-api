package com.bootcamp.biopark.infraestructure.security.config;

import com.bootcamp.biopark.infraestructure.security.JwtAuthenticationFilter;
import com.bootcamp.biopark.infraestructure.security.SecurityErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    JwtAuthenticationFilter jwtFilter,
                                    SecurityErrorHandler errorHandler) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable) // csrf -> csrf.disable()
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize //rutas publicas primero
                                .requestMatchers("/api/v1/auth/**", "/error").permitAll()
                                // rutas con acceso protegido
                                .requestMatchers(HttpMethod.GET, "/api/v1/visitors/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/api/v1/visitors/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/environment/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/api/v1/environment/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/rate/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/api/v1/rate/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                                .exceptionHandling(e ->
                                e
                                .authenticationEntryPoint(errorHandler)
                                .accessDeniedHandler(errorHandler))
                //filtro personalizado
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
