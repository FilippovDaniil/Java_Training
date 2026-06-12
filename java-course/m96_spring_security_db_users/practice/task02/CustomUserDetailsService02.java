package m96_spring_security_db_users.practice.task02;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@Service
class CustomUserDetailsService02 implements UserDetailsService {
    private final UserRepository02 repo;
    CustomUserDetailsService02(UserRepository02 repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: UserEntity02 u = repo.findByUsername(username)
        // TODO:     .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        // TODO: return User.withUsername(u.getUsername()).password(u.getPassword())
        // TODO:     .authorities("ROLE_USER").disabled(!u.isEnabled()).build();
        return null;
    }
}
