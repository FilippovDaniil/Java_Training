package m93_spring_security_intro.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
class MeController05 {
    @GetMapping("/api/me/principal")
    String viaPrincipal(Principal principal) {
        // TODO: return principal.getName();
        return null;
    }

    @GetMapping("/api/me/auth")
    String viaAuthentication(Authentication auth) {
        // TODO: return auth.getName();
        return null;
    }

    @GetMapping("/api/me/details")
    String viaPrincipalAnnotation(@AuthenticationPrincipal UserDetails user) {
        // TODO: return user.getUsername();
        return null;
    }
}
