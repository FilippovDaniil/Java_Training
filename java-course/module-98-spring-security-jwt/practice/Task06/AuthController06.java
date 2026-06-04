import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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

@RestController
@RequestMapping("/api/auth")
class AuthController06 {
    private final AuthenticationManager authManager;
    private final JwtService06 jwtService;
    AuthController06(AuthenticationManager authManager, JwtService06 jwtService) {
        this.authManager = authManager; this.jwtService = jwtService;
    }

    @PostMapping("/login")
    TokenResponse login(@RequestBody LoginRequest req) {
        // TODO: Authentication auth = authManager.authenticate(
        // TODO:     new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        // TODO: List<String> roles = auth.getAuthorities().stream()
        // TODO:     .map(GrantedAuthority::getAuthority).toList();
        // TODO: return new TokenResponse(jwtService.generateToken(auth.getName(), roles));
        return null;
    }
}
