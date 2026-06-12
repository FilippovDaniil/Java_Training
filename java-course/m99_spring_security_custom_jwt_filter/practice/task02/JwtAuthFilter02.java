package m99_spring_security_custom_jwt_filter.practice.task02;

/**
 * Задача 02 — Модуль 99: скелет фильтра OncePerRequestFilter
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   Создайте каркас JWT-фильтра. Реальную установку контекста добавим в задаче 03.
 *     1) Класс JwtAuthFilter02 extends OncePerRequestFilter, помечен @Component.
 *     2) Внедрите JwtService02 через конструктор.
 *     3) В doFilterInternal:
 *          - прочитать заголовок Authorization;
 *          - если он null ИЛИ не начинается с "Bearer " → chain.doFilter(req, res); return;
 *          - иначе вырезать токен (substring(7)) и пока просто залогировать его длину;
 *          - в конце ОБЯЗАТЕЛЬНО chain.doFilter(req, res).
 *
 * ЦЕЛЬ: освоить переопределение doFilterInternal и правило «chain.doFilter — всегда».
 *
 * ВАЖНО: в OncePerRequestFilter метод doFilter — final; переопределяют только doFilterInternal.
 *
 * ПОДСКАЗКА: JwtService02 здесь — заглушка-зависимость (как реальный сервис из модуля 98).
 */

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

class JwtAuthFilter02 extends OncePerRequestFilter {

    private final JwtService02 jwtService;
    JwtAuthFilter02(JwtService02 jwtService) { this.jwtService = jwtService; }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        // TODO: String header = request.getHeader("Authorization");
        // TODO: if (header == null || !header.startsWith("Bearer ")) { chain.doFilter(request, response); return; }
        // TODO: String token = header.substring(7);
        // TODO: System.out.println("Получен токен длиной " + token.length());
        // TODO: chain.doFilter(request, response);   // ← всегда продолжаем цепочку
    }
}
