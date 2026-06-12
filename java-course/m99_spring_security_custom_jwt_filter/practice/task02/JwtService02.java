package m99_spring_security_custom_jwt_filter.practice.task02;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Service
class JwtService02 {
    // Заглушка зависимости. В реальном проекте — сервис из модуля 98 (generate/extract/isValid).
    boolean isValid(String token) {
        // TODO: try-parse jjwt; здесь упрощённо:
        return token != null && !token.isBlank();
    }
    String extractUsername(String token) { return "alice"; }
}
