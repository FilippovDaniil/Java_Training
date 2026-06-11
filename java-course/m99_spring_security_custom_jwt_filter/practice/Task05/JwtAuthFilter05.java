package m99_spring_security_custom_jwt_filter.practice.task05;

/**
 * Задача 05 — Модуль 99: обработка отсутствия/невалидного токена
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   Сделайте фильтр устойчивым: он НЕ должен ронять запрос на плохом токене,
 *   а просто не аутентифицировать его (дальше авторизация вернёт 401/403).
 *   В doFilterInternal обработайте все ветки:
 *     1) нет заголовка / не "Bearer " → chain.doFilter; return; (контекст не трогаем);
 *     2) есть токен → оберните разбор в try/catch (JwtException e):
 *          - успех  → установить Authentication в контекст;
 *          - ошибка → НЕ ставить контекст (можно залогировать), НЕ бросать дальше;
 *     3) в конце — chain.doFilter(req, res) в любом случае.
 *
 * ЦЕЛЬ: усвоить принцип «фильтр не решает про 401 — он лишь (не)аутентифицирует;
 *       финальный вердикт даёт слой авторизации».
 *
 * ТАБЛИЦА ИСХОДОВ:
 *   токена нет           → контекст пуст → /api/** = 401, публичное = 200
 *   токен валиден        → контекст = user → доступ по правам (200/403)
 *   токен битый/истёкший → контекст пуст → 401 (а не 500!)
 *
 * ПОДСКАЗКА: JwtException — базовый для Expired/Signature/Malformed (см. модуль 98).
 */

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

class JwtAuthFilter05 extends OncePerRequestFilter {

    private final JwtService05 jwtService;
    JwtAuthFilter05(JwtService05 jwtService) { this.jwtService = jwtService; }

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
        // TODO:     String username = jwtService.extractUsername(token);   // бросит JwtException если токен плох
        // TODO:     List<String> roles = jwtService.extractRoles(token);
        // TODO:     var authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
        // TODO:     var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // TODO:     SecurityContextHolder.getContext().setAuthentication(auth);
        // TODO: } catch (JwtException e) {
        // TODO:     // невалидный токен: контекст не трогаем, исключение НЕ пробрасываем
        // TODO:     System.out.println("Невалидный JWT: " + e.getMessage());
        // TODO: }
        chain.doFilter(request, response);   // всегда
    }
}
