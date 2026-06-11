package m98_spring_security_jwt.practice;

/**
 * Задача 02 — Модуль 98: генерация токена (jjwt 0.12.x — builder)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   Реализуйте метод generate(username, roles, ttlMillis):
 *     return Jwts.builder()
 *         .subject(username)
 *         .claim("roles", roles)
 *         .issuedAt(new Date())
 *         .expiration(new Date(System.currentTimeMillis() + ttlMillis))
 *         .signWith(KEY)
 *         .compact();
 *   В main: сгенерируйте токен для "alice" с ролями [ROLE_USER] на 1 час и напечатайте.
 *
 * ЦЕЛЬ: освоить актуальный API выдачи токена в jjwt 0.12.x.
 *
 * ПОДСКАЗКА: в 0.12.x методы БЕЗ префикса set: subject() (не setSubject), expiration()
 *            (не setExpiration); алгоритм подписи выводится из типа ключа в signWith.
 */
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class Task02 {
    private static final SecretKey KEY =
            Keys.hmacShaKeyFor("01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8));

    public static void main(String[] args) {
        // TODO: String token = generate("alice", List.of("ROLE_USER"), 3_600_000);
        // TODO: System.out.println(token);
    }

    static String generate(String username, List<String> roles, long ttlMillis) {
        // TODO: return Jwts.builder()
        // TODO:     .subject(username)
        // TODO:     .claim("roles", roles)
        // TODO:     .issuedAt(new Date())
        // TODO:     .expiration(new Date(System.currentTimeMillis() + ttlMillis))
        // TODO:     .signWith(KEY)
        // TODO:     .compact();
        return null;
    }
}
