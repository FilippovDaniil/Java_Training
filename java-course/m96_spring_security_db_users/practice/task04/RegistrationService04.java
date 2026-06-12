package m96_spring_security_db_users.practice.task04;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
class RegistrationService04 {
    private final UserRepository04 repo;
    private final PasswordEncoder encoder;
    RegistrationService04(UserRepository04 repo, PasswordEncoder encoder) { this.repo = repo; this.encoder = encoder; }

    @Transactional
    public UserEntity04 register(String username, String rawPassword) {
        // TODO: if (repo.existsByUsername(username)) throw new IllegalStateException("Имя занято: " + username);
        // TODO: UserEntity04 u = new UserEntity04(username, encoder.encode(rawPassword), Set.of("USER"));
        // TODO: return repo.save(u);
        return null;
    }
}
