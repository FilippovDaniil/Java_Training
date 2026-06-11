package m98_spring_security_jwt.practice.task06;

/**
 * Задача 06 — Модуль 98: endpoint логина — выдача JWT через AuthenticationManager
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   1) Бин AuthenticationManager из AuthenticationConfiguration:
 *        @Bean AuthenticationManager authManager(AuthenticationConfiguration cfg) throws Exception {
 *            return cfg.getAuthenticationManager(); }
 *   2) AuthController06.login(LoginRequest):
 *        Authentication auth = authManager.authenticate(
 *            new UsernamePasswordAuthenticationToken(req.username(), req.password()));
 *        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
 *        String token = jwtService.generateToken(auth.getName(), roles);
 *        return new TokenResponse(token);
 *   3) SecurityFilterChain: "/api/auth/**" permitAll; остальное authenticated;
 *      sessionCreationPolicy(STATELESS); csrf.disable.
 *   4) Пользователь alice/password (in-memory, BCrypt). Проверьте: POST /api/auth/login
 *      с верными кредами → {token}; с неверными → 401.
 *
 * ЦЕЛЬ: связать проверку пароля (AuthenticationManager) с выдачей JWT.
 *
 * ПОДСКАЗКА: authenticate() сам бросит исключение при неверном пароле → 401. Использование
 *            токена в защищённых запросах появится в модуле 99 (custom JWT filter).
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
