/**
 * Задача 05 — Модуль 98: JwtService — секрет и срок жизни из конфигурации
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   Оформите работу с токеном как бин-сервис, конфигурируемый извне.
 *   1) В application.properties (образец в SETTINGS-блоке JavaDoc):
 *        jwt.secret=01234567890123456789012345678901   # ≥ 32 байт; в проде — из env!
 *        jwt.expiration-ms=3600000
 *   2) JwtService05:
 *        - конструктор @Value("${jwt.secret}") String secret, @Value("${jwt.expiration-ms:3600000}") long ttl;
 *          this.key = Keys.hmacShaKeyFor(secret.getBytes(UTF_8));
 *        - String generateToken(String username, Collection<String> roles);
 *        - String extractUsername(String token)  → claims.getSubject();
 *        - boolean isValid(String token)          → try-parse/JwtException.
 *   3) В CommandLineRunner: сгенерируйте токен, извлеките username, проверьте isValid.
 *
 * ЦЕЛЬ: инкапсулировать JWT-логику в сервис с конфигом (готов к использованию в фильтре, модуль 99).
 *
 * ПОДСКАЗКА: секрет НИКОГДА не хардкодить в проде — @Value из properties/env (см. модуль 84).
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
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }

    @Bean
    CommandLineRunner demo(JwtService05 jwt) {
        return args -> {
            // TODO: String token = jwt.generateToken("alice", List.of("ROLE_USER"));
            // TODO: System.out.println("token = " + token);
            // TODO: System.out.println("user  = " + jwt.extractUsername(token));   // alice
            // TODO: System.out.println("valid = " + jwt.isValid(token));            // true
        };
    }
}

@Service
class JwtService05 {
    private final SecretKey key;
    private final long expirationMs;

    JwtService05(@Value("${jwt.secret}") String secret,
                 @Value("${jwt.expiration-ms:3600000}") long expirationMs) {
        // TODO: this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        // TODO: this.expirationMs = expirationMs;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    String generateToken(String username, Collection<String> roles) {
        // TODO: return Jwts.builder().subject(username).claim("roles", roles)
        // TODO:     .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expirationMs))
        // TODO:     .signWith(key).compact();
        return null;
    }

    String extractUsername(String token) {
        // TODO: Claims c = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        // TODO: return c.getSubject();
        return null;
    }

    boolean isValid(String token) {
        // TODO: try { Jwts.parser().verifyWith(key).build().parseSignedClaims(token); return true; }
        // TODO: catch (JwtException e) { return false; }
        return false;
    }
}
