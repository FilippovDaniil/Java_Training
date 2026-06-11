package m96_spring_security_db_users.practice.task03;

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
import org.springframework.security.core.GrantedAuthority;
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
class CustomUserDetailsService03 implements UserDetailsService {
    private final UserRepository03 repo;
    CustomUserDetailsService03(UserRepository03 repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity03 u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        // TODO: List<GrantedAuthority> auth = u.getRoles().stream()
        // TODO:     .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
        // TODO:     .collect(Collectors.toList());
        // TODO: return User.withUsername(u.getUsername()).password(u.getPassword()).authorities(auth).build();
        return null;
    }
}
