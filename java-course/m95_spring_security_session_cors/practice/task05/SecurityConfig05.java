package m95_spring_security_session_cors.practice.task05;

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

@Configuration
@EnableWebSecurity
class SecurityConfig05 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // TODO: http.formLogin(Customizer.withDefaults());
        // TODO: http.logout(logout -> logout
        // TODO:     .logoutUrl("/api/auth/logout")
        // TODO:     .invalidateHttpSession(true)
        // TODO:     .deleteCookies("JSESSIONID")
        // TODO:     .logoutSuccessHandler((req, res, auth) -> res.setStatus(200)));
        // TODO: http.csrf(csrf -> csrf.disable());  // упрощение для теста POST logout
        return http.build();
    }
}
