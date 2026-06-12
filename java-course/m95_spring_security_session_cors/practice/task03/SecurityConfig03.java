package m95_spring_security_session_cors.practice.task03;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
class SecurityConfig03 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // TODO: CorsConfiguration cfg = new CorsConfiguration();
        // TODO: cfg.setAllowedOrigins(List.of("http://localhost:3000"));
        // TODO: cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // TODO: cfg.setAllowedHeaders(List.of("*"));
        // TODO: cfg.setAllowCredentials(true);
        // TODO: UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        // TODO: src.registerCorsConfiguration("/**", cfg);
        // TODO: return src;
        return null;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.cors(Customizer.withDefaults());
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
