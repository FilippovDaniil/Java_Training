package m99_spring_security_custom_jwt_filter.practice.task05;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
class JwtService05 {

    private final SecretKey key;

    JwtService05(@Value("${jwt.secret}") String base64Secret) {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret));
    }

    // Бросает JwtException при плохом токене (имитация реального parse из модуля 98).
    String extractUsername(String token) {
        // TODO: return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
    List<String> extractRoles(String token) { return List.of("ROLE_USER"); }
}
