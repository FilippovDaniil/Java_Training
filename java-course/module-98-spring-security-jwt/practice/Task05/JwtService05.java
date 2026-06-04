import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
class JwtService05 {
    private final SecretKey key;
    private final long expirationMs;

    JwtService05(@Value("${jwt.secret}") String secret,
                 @Value("${jwt.expiration-ms:3600000}") long expirationMs) {
        // TODO: this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        // TODO: this.expirationMs = expirationMs;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    String generateToken(String username, Collection<String> roles) {
        // TODO: return Jwts.builder().subject(username).claim("roles", roles)
        // TODO:     .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expirationMs))
        // TODO:     .signWith(key).compact();
        return null;
    }

    String extractUsername(String token) {
        // TODO: Claims c = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        // TODO: return c.getSubject();
        return null;
    }

    boolean isValid(String token) {
        // TODO: try { Jwts.parser().verifyWith(key).build().parseSignedClaims(token); return true; }
        // TODO: catch (JwtException e) { return false; }
        return false;
    }
}
