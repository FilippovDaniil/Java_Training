/**
 * Задача 04 — Модуль 98: обработка невалидных токенов (истёкший, подделанный)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   Реализуйте isValid(token), который безопасно проверяет токен и НЕ падает.
 *   1) isValid(token): try { Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token); return true; }
 *        catch (JwtException e) { return false; }   // любая проблема → невалиден
 *   2) В main проверьте три случая:
 *        a) валидный токен (свежий) → isValid == true;
 *        b) ИСТЁКШИЙ токен (expiration в прошлом, например now - 1000) → isValid == false
 *           (внутри бросится ExpiredJwtException — наследник JwtException);
 *        c) ПОДДЕЛАННЫЙ токен: возьмите валидный и измените один символ в payload →
 *           isValid == false (SignatureException).
 *   3) Напечатайте результаты трёх проверок.
 *
 * ЦЕЛЬ: научиться устойчиво валидировать токены, перехватывая JwtException.
 *
 * ПОДСКАЗКА: ExpiredJwtException и io.jsonwebtoken.security.SignatureException — оба
 *            наследники JwtException, поэтому достаточно поймать базовый JwtException.
 */
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class Task04 {
    private static final SecretKey KEY =
            Keys.hmacShaKeyFor("01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8));

    public static void main(String[] args) {
        String valid = build(3_600_000);     // +1 час
        String expired = build(-1000);       // уже истёк

        // подделка: меняем символ в payload-части
        String[] p = valid.split("\\.");
        String tampered = p[0] + "." + p[1] + "x." + p[2];

        // TODO: System.out.println("валидный:   " + isValid(valid));     // true
        // TODO: System.out.println("истёкший:   " + isValid(expired));   // false
        // TODO: System.out.println("подделка:   " + isValid(tampered));  // false
    }

    static String build(long ttlMillis) {
        return Jwts.builder().subject("alice").claim("roles", List.of("ROLE_USER"))
                .issuedAt(new Date(System.currentTimeMillis() - 10_000))
                .expiration(new Date(System.currentTimeMillis() + ttlMillis))
                .signWith(KEY).compact();
    }

    static boolean isValid(String token) {
        // TODO: try { Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token); return true; }
        // TODO: catch (JwtException e) { return false; }
        return false;
    }
}
