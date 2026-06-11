package m93_spring_security_intro.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
class CurrentUserService04 {
    String currentUsername() {
        // TODO: Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // TODO: return (auth != null && auth.isAuthenticated()) ? auth.getName() : "anonymous";
        return "?";
    }

    String currentAuthorities() {
        // TODO: Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // TODO: return auth == null ? "[]" : auth.getAuthorities().toString();
        return "[]";
    }
}
