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

@Service
class JwtService05 {
    // Бросает JwtException при плохом токене (имитация реального parse из модуля 98).
    String extractUsername(String token) {
        // TODO: return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        return "alice";
    }
    List<String> extractRoles(String token) { return List.of("ROLE_USER"); }
}
