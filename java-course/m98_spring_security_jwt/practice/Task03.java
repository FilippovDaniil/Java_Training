package m98_spring_security_jwt.practice;

/**
 * Задача 03 — Модуль 98: разбор и валидация токена (jjwt 0.12.x — parser)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   1) Сгенерируйте токен для "alice" (как в Task02).
 *   2) Разберите его и извлеките данные:
 *        Claims claims = Jwts.parser()
 *            .verifyWith(KEY)            // проверка подписи
 *            .build()
 *            .parseSignedClaims(token)
 *            .getPayload();
 *        claims.getSubject() → "alice"; claims.get("roles") → список; claims.getExpiration().
 *   3) Напечатайте subject, roles, exp.
 *
 * ЦЕЛЬ: освоить разбор токена и извлечение claims в jjwt 0.12.x.
 *
 * ПОДСКАЗКА: в 0.12.x: parser().verifyWith(key).build().parseSignedClaims(token).getPayload()
 *            (а НЕ parseClaimsJws(...).getBody() из старого API 0.11.x).
 */
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class Task03 {
    private static final SecretKey KEY =
            Keys.hmacShaKeyFor("01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8));

    public static void main(String[] args) {
        String token = Jwts.builder()
                .subject("alice")
                .claim("roles", List.of("ROLE_USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3_600_000))
                .signWith(KEY)
                .compact();

        // TODO: Claims claims = Jwts.parser()
        // TODO:     .verifyWith(KEY)
        // TODO:     .build()
        // TODO:     .parseSignedClaims(token)
        // TODO:     .getPayload();
        // TODO: System.out.println("subject = " + claims.getSubject());      // alice
        // TODO: System.out.println("roles   = " + claims.get("roles"));      // [ROLE_USER]
        // TODO: System.out.println("exp     = " + claims.getExpiration());
    }
}
