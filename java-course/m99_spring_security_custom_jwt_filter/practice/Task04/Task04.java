package m99_spring_security_custom_jwt_filter.practice.task04;

/**
 * Задача 04 — Модуль 99: регистрация фильтра в цепочке (addFilterBefore)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   Подключите JWT-фильтр в SecurityFilterChain и включите stateless-режим.
 *   В бине SecurityFilterChain настройте http:
 *     1) authorizeHttpRequests:
 *          - "/api/auth/**" → permitAll();
 *          - anyRequest()   → authenticated();
 *     2) sessionManagement → SessionCreationPolicy.STATELESS;
 *     3) csrf → disable();
 *     4) addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).
 *   Фильтр JwtAuthFilter04 (готов ниже) внедряется в метод-бин как параметр.
 *
 * ЦЕЛЬ: понять, ПОЧЕМУ фильтр ставят ПЕРЕД UsernamePasswordAuthenticationFilter
 *       (чтобы к моменту проверки прав пользователь уже был в контексте).
 *
 * ВАЖНО: без регистрации фильтра валидный токен игнорируется → 401 на /api/**.
 *
 * ПОДСКАЗКА: addFilterBefore(нашФильтр, UsernamePasswordAuthenticationFilter.class).
 */

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class Task04 {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter04 jwtFilter) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:         .requestMatchers("/api/auth/**").permitAll()
        // TODO:         .anyRequest().authenticated())
        // TODO:     .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // TODO:     .csrf(csrf -> csrf.disable())
        // TODO:     .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
