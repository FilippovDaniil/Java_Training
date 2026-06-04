import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
class JwtService07 {
    private final SecretKey key;
    private final long expirationMs;

    JwtService07(@Value("${jwt.secret:01234567890123456789012345678901}") String secret,
                 @Value("${jwt.expiration-ms:3600000}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    String generateToken(String username, List<String> roles) {
        // TODO: return Jwts.builder().subject(username).claim("roles", roles)
        // TODO:     .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expirationMs))
        // TODO:     .signWith(key).compact();
        return null;
    }

    String extractUsername(String token) {
        // TODO: return claims(token).getSubject();
        return null;
    }

    Object extractRoles(String token) {
        // TODO: return claims(token).get("roles");
        return null;
    }

    boolean isValid(String token) {
        // TODO: try { claims(token); return true; } catch (JwtException e) { return false; }
        return false;
    }

    private Claims claims(String token) {
        // TODO: return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return null;
    }
}
