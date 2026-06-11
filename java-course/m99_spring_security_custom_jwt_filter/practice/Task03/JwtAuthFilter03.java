package m99_spring_security_custom_jwt_filter.practice.task03;

/**
 * Задача 03 — Модуль 99: установка Authentication в SecurityContext
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security,
 *   io.jsonwebtoken:jjwt-api:0.12.x (+ jjwt-impl, jjwt-jackson в runtime).
 *
 * ЗАДАНИЕ:
 *   Дополните фильтр из задачи 02 — после валидного токена аутентифицируйте пользователя.
 *   В doFilterInternal, когда токен извлечён и jwtService.isValid(token):
 *     1) String username = jwtService.extractUsername(token);
 *     2) List<String> roles = jwtService.extractRoles(token);            // напр. ["ROLE_USER"]
 *     3) authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
 *     4) var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
 *          // principal=username, credentials=null (пароль уже не нужен), authorities — для hasRole
 *     5) SecurityContextHolder.getContext().setAuthentication(auth);
 *   В конце — chain.doFilter(req, res) (всегда).
 *
 * ЦЕЛЬ: понять, как из claims собирается Authentication и почему credentials = null.
 *
 * ВАЖНО: роль в токене должна нести префикс ROLE_ (ROLE_USER), иначе hasRole("USER") не сработает.
 *
 * ПОДСКАЗКА: principal — это имя из claim sub; authorities — из claim roles.
 */

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

class JwtAuthFilter03 extends OncePerRequestFilter {

    private final JwtService03 jwtService;
    JwtAuthFilter03(JwtService03 jwtService) { this.jwtService = jwtService; }

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

        // TODO: if (jwtService.isValid(token)) {
        // TODO:     String username = jwtService.extractUsername(token);
        // TODO:     List<String> roles = jwtService.extractRoles(token);
        // TODO:     var authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
        // TODO:     var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // TODO:     SecurityContextHolder.getContext().setAuthentication(auth);
        // TODO: }
        chain.doFilter(request, response);
    }
}
