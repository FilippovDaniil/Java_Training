package m97_spring_security_authorization.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/tasks")
class TaskController04 {
    private final TaskService04 service;
    TaskController04(TaskService04 service) { this.service = service; }

    @PutMapping("/{id}")
    String update(@PathVariable Long id, Authentication auth) {
        // TODO: boolean isAdmin = auth.getAuthorities().stream()
        // TODO:     .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        // TODO: return service.updateTask(id, auth.getName(), isAdmin);
        return null;
    }
}
