import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.CommandLineRunner;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
class RegistrationService07 {
    private final UserRepository07 repo;
    private final PasswordEncoder encoder;
    RegistrationService07(UserRepository07 repo, PasswordEncoder encoder) { this.repo = repo; this.encoder = encoder; }

    @Transactional
    public UserEntity07 register(String username, String rawPassword) {
        // TODO: if (repo.existsByUsername(username)) throw new IllegalStateException("Имя занято: " + username);
        // TODO: return repo.save(new UserEntity07(username, encoder.encode(rawPassword), Set.of("USER")));
        return null;
    }
}
