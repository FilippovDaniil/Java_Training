package m99_spring_security_custom_jwt_filter.practice.task07;

/**
 * Задача 07 — Модуль 99: МИНИ-ПРОЕКТ «Полный JWT-флоу Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЦЕЛЬ: замкнуть stateless-аутентификацию — токен из модуля 98 теперь РЕАЛЬНО авторизует
 *       запросы через собственный JWT-фильтр.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) JwtService07 (из модуля 98): generateToken(username, roles), extractUsername(token),
 *      extractRoles(token), isValid(token). Роли в токен класть С префиксом ROLE_.
 *
 *   2) AuthController07: POST /api/auth/login (LoginRequest) — через AuthenticationManager
 *      проверить креды, вернуть TokenResponse{token}.
 *
 *   3) JwtAuthFilter07 extends OncePerRequestFilter:
 *        - читает Authorization: Bearer <token>;
 *        - нет/не Bearer → chain.doFilter; return;
 *        - try: extractUsername+extractRoles → authorities (SimpleGrantedAuthority)
 *               → UsernamePasswordAuthenticationToken(username, null, authorities)
 *               → SecurityContextHolder.setAuthentication;
 *          catch (JwtException) → не аутентифицировать;
 *        - всегда chain.doFilter.
 *
 *   4) SecurityFilterChain07:
 *        - "/api/auth/**" permitAll;
 *        - "/api/admin/**" hasRole("ADMIN");
 *        - "/api/**" authenticated;
 *        - STATELESS; csrf.disable;
 *        - addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).
 *
 *   5) AuthenticationManager-бин из AuthenticationConfiguration; пользователи in-memory BCrypt:
 *        alice/password (USER), admin/admin (ADMIN, USER).
 *
 *   6) Контроллеры: TaskController07 (GET /api/tasks — для любого аутентифицированного),
 *      AdminController07 (GET /api/admin/stats — только ADMIN).
 *
 * ОЖИДАЕМЫЙ ФЛОУ (ручная проверка curl):
 *   POST /api/auth/login alice/password           → 200 {token}
 *   GET  /api/tasks  (без токена)                  → 401
 *   GET  /api/tasks  Bearer <alice-token>          → 200
 *   GET  /api/admin/stats  Bearer <alice-token>    → 403 (нет ADMIN)
 *   GET  /api/admin/stats  Bearer <admin-token>    → 200
 *   GET  /api/tasks  Bearer <битый-токен>          → 401 (а не 500)
 *
 * АРХИТЕКТУРА:
 *   login → AuthenticationManager(пароль) → JwtService.generateToken → {token}
 *   каждый запрос → JwtAuthFilter читает Bearer → SecurityContext → authorizeHttpRequests
 *
 * ПОДСКАЗКА: соберите модуль 98 (выдача + AuthenticationManager) + фильтр из задач 03–05 этого модуля.
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
