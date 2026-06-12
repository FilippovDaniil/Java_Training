package m96_spring_security_db_users.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

@Service
class CustomUserDetailsService05 implements UserDetailsService {
    private final UserRepository05 repo;
    CustomUserDetailsService05(UserRepository05 repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity05 u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        // TODO: var auth = u.getRoles().stream()
        // TODO:     .map(r -> new SimpleGrantedAuthority(r.getName()))   // имя уже с ROLE_
        // TODO:     .collect(Collectors.toList());
        // TODO: return User.withUsername(u.getUsername()).password(u.getPassword()).authorities(auth).build();
        return null;
    }
}
