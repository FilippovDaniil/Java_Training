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

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
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

    @SuppressWarnings("unchecked")
    List<String> extractRoles(String token) {
        // TODO: return (List<String>) claims(token).get("roles");
        return List.of();
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
    @GetMapping
    List<String> list() { return List.of("Задача 1", "Задача 2"); }
}

@RestController
@RequestMapping("/api/admin")
class AdminController07 {
    @GetMapping("/stats")
    String stats() { return "users=2, tasks=2"; }
}

@Component
class JwtAuthFilter07 extends OncePerRequestFilter {
    private final JwtService07 jwtService;
    JwtAuthFilter07(JwtService07 jwtService) { this.jwtService = jwtService; }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);
        // TODO: try {
        // TODO:     if (jwtService.isValid(token)) {
        // TODO:         String username = jwtService.extractUsername(token);
        // TODO:         var authorities = jwtService.extractRoles(token).stream()
        // TODO:                 .map(SimpleGrantedAuthority::new).toList();
        // TODO:         var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // TODO:         SecurityContextHolder.getContext().setAuthentication(auth);
        // TODO:     }
        // TODO: } catch (JwtException e) {
        // TODO:     System.out.println("Невалидный JWT: " + e.getMessage());
        // TODO: }
        chain.doFilter(request, response);
    }
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
    SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter07 jwtFilter) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:         .requestMatchers("/api/auth/**").permitAll()
        // TODO:         .requestMatchers("/api/admin/**").hasRole("ADMIN")
        // TODO:         .requestMatchers("/api/**").authenticated()
        // TODO:         .anyRequest().authenticated())
        // TODO:     .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // TODO:     .csrf(csrf -> csrf.disable())
        // TODO:     .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
