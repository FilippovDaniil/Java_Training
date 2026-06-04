/**
 * Задача 01 — Модуль 98: структура JWT — собрать токен и рассмотреть 3 части
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime). bare-javac не компилируется.
 *
 * ЗАДАНИЕ:
 *   1) Создайте секретный ключ (≥ 256 бит):
 *        SecretKey key = Keys.hmacShaKeyFor("01234567890123456789012345678901".getBytes(UTF_8));
 *   2) Соберите токен с subject="alice" и claim roles=["ROLE_USER"], exp +1 час.
 *   3) Напечатайте токен и РАЗБЕЙТЕ его по точкам на 3 части (header.payload.signature):
 *        String[] parts = token.split("\\.");
 *        выведите количество частей (3) и декодируйте первые две из base64url:
 *        new String(Base64.getUrlDecoder().decode(parts[0])) → {"alg":"HS256"}
 *        new String(Base64.getUrlDecoder().decode(parts[1])) → {"sub":"alice",...}
 *   4) Сделайте вывод: payload ЧИТАЕМ (base64, не шифрование) — секреты в JWT не кладём.
 *
 * ЦЕЛЬ: понять формат header.payload.signature и что payload не зашифрован.
 *
 * ПОДСКАЗКА: signature (parts[2]) не декодируется в текст — это бинарная подпись.
 */
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        SecretKey key = Keys.hmacShaKeyFor("01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8));

        // TODO: String token = Jwts.builder()
        // TODO:     .subject("alice")
        // TODO:     .claim("roles", List.of("ROLE_USER"))
        // TODO:     .issuedAt(new Date())
        // TODO:     .expiration(new Date(System.currentTimeMillis() + 3_600_000))
        // TODO:     .signWith(key)
        // TODO:     .compact();
        // TODO: System.out.println("token = " + token);

        // TODO: String[] parts = token.split("\\.");
        // TODO: System.out.println("частей: " + parts.length);   // 3
        // TODO: System.out.println("header:  " + new String(Base64.getUrlDecoder().decode(parts[0])));
        // TODO: System.out.println("payload: " + new String(Base64.getUrlDecoder().decode(parts[1])));
        // TODO: System.out.println("payload читаем без ключа → секреты в JWT не кладём!");
    }
}
