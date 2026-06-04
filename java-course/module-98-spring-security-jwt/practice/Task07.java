/**
 * Задача 07 — Модуль 98: МИНИ-ПРОЕКТ «JWT-аутентификация Task Tracker (выдача токена)»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЦЕЛЬ: перевести вход Task Tracker на stateless JWT: сервис токенов + endpoint логина,
 *       выдающий подписанный токен. Проверку токена в каждом запросе добавим в модуле 99.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) JwtService07 (секрет/срок из @Value):
 *        - generateToken(username, roles): subject + claim roles + iat + exp + signWith;
 *        - extractUsername(token): parse → getSubject;
 *        - extractRoles(token): claims.get("roles");
 *        - isValid(token): try-parse / JwtException → false.
 *
 *   2) AuthController07: POST /api/auth/login (LoginRequest) — через AuthenticationManager
 *      проверить креды, выдать TokenResponse{token}.
 *
 *   3) SecurityFilterChain07:
 *        - "/api/auth/**" permitAll;
 *        - "/api/**" authenticated;
 *        - sessionCreationPolicy(STATELESS);
 *        - csrf.disable.
 *
 *   4) AuthenticationManager-бин из AuthenticationConfiguration.
 *
 *   5) Пользователи: alice/password (USER), admin/admin (ADMIN, USER) — in-memory, BCrypt.
 *
 *   6) Демонстрация (CommandLineRunner): выдать токен alice через JwtService07 напрямую,
 *      извлечь username/roles, проверить isValid — вывести в лог (имитация будущего фильтра).
 *
 * АРХИТЕКТУРА (мост к модулю 99):
 *   POST /api/auth/login → AuthenticationManager (проверка пароля) → JwtService.generateToken → {token}
 *   клиент шлёт Authorization: Bearer <token>  ──►  [фильтр чтения токена — модуль 99]
 *   stateless: сессии не создаются (STATELESS)
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - POST /api/auth/login alice/password → {token}; неверные креды → 401;
 *   - /api/** пока недоступно по токену (фильтра ещё нет — это норма, добавим в 99);
 *   - JwtService корректно генерирует/разбирает/валидирует токен (видно в логе runner'а).
 *
 * ПОДСКАЗКА: соберите Task05 (JwtService) + Task06 (login через AuthenticationManager).
 *            STATELESS — ключевая настройка JWT-режима.
 */
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }

    @Bean
    CommandLineRunner demo(JwtService07 jwt) {
        return args -> {
            // TODO: String token = jwt.generateToken("alice", List.of("ROLE_USER"));
            // TODO: System.out.println("token = " + token);
            // TODO: System.out.println("user  = " + jwt.extractUsername(token));
            // TODO: System.out.println("valid = " + jwt.isValid(token));
        };
    }
}

record LoginRequest(String username, String password) {}
record TokenResponse(String token) {}

@Service
class JwtService07 {
    private final SecretKey key;
    private final long expirationMs;

    JwtService07(@Value("${jwt.secret:01234567890123456789012345678901}") String secret,
                 @Value("${jwt.expiration-ms:3600000}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    String generateToken(String username, List<String> roles) {
        // TODO: return Jwts.builder().subject(username).claim("roles", roles)
        // TODO:     .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expirationMs))
        // TODO:     .signWith(key).compact();
        return null;
    }

    String extractUsername(String token) {
        // TODO: return claims(token).getSubject();
        return null;
    }

    Object extractRoles(String token) {
        // TODO: return claims(token).get("roles");
        return null;
    }

    boolean isValid(String token) {
        // TODO: try { claims(token); return true; } catch (JwtException e) { return false; }
        return false;
    }

    private Claims claims(String token) {
        // TODO: return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return null;
    }
}

@RestController
@RequestMapping("/api/auth")
class AuthController07 {
    private final AuthenticationManager authManager;
    private final JwtService07 jwtService;
    AuthController07(AuthenticationManager authManager, JwtService07 jwtService) {
        this.authManager = authManager; this.jwtService = jwtService;
    }

    @PostMapping("/login")
    TokenResponse login(@RequestBody LoginRequest req) {
        // TODO: Authentication auth = authManager.authenticate(
        // TODO:     new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        // TODO: List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        // TODO: return new TokenResponse(jwtService.generateToken(auth.getName(), roles));
        return null;
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    // Станет доступным по токену после добавления JWT-фильтра в модуле 99.
    @GetMapping
    List<String> list() { return List.of("Задача 1", "Задача 2"); }
}

@Configuration
@EnableWebSecurity
class SecurityConfig07 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build(),
                User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN", "USER").build());
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/api/auth/**").permitAll()
        // TODO:     .requestMatchers("/api/**").authenticated()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
