package com.project.EMS.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.EMS.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf-> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request ->
//                    request.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                    .anyRequest().authenticated()
                )
        .exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, authException) ->{
                authException.printStackTrace();
                response.setStatus(401);
                response.setContentType("application/json");
                String message = authException.getMessage();
                Map<String, String> errorMap = Map.of("message", message, "status", String.valueOf(401),"statusCode", String.valueOf(401));
                var objectMapper = new ObjectMapper();

            response.getWriter().write(objectMapper.writeValueAsString(errorMap));
                }
        ));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

}
