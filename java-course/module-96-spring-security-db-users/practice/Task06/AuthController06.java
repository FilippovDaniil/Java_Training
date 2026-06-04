import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
class AuthController06 {
    private final RegistrationService06 service;
    AuthController06(RegistrationService06 service) { this.service = service; }

    @PostMapping("/register")
    ResponseEntity<UserResponse06> register(@Valid @RequestBody RegisterRequest06 req) {
        // TODO: UserEntity06 u = service.register(req.username(), req.password());
        // TODO: return ResponseEntity.status(HttpStatus.CREATED)
        // TODO:     .body(new UserResponse06(u.getId(), u.getUsername()));
        return null;
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<String> duplicate(IllegalStateException e) {
        // TODO: return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        return null;
    }
}
