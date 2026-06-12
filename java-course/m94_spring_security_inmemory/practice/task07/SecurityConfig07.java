package m94_spring_security_inmemory.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Configuration
@EnableWebSecurity
class SecurityConfig07 {
    @Bean
    PasswordEncoder passwordEncoder() {
        // TODO: return new BCryptPasswordEncoder();
        return null;
    }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        // TODO: var alice = User.withUsername("alice").password(enc.encode("password")).roles("USER").build();
        // TODO: var admin = User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN", "USER").build();
        // TODO: return new InMemoryUserDetailsManager(alice, admin);
        return null;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/actuator/health").permitAll()
        // TODO:     .requestMatchers("/api/admin/**").hasRole("ADMIN")
        // TODO:     .requestMatchers("/api/**").authenticated()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
