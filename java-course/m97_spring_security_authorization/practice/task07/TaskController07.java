package m97_spring_security_authorization.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
class TaskController07 {
    private final TaskService07 service;
    TaskController07(TaskService07 service) { this.service = service; }

    @GetMapping("/api/tasks") List<Task07Dto> list() { return service.findVisible(); }
    @PutMapping("/api/tasks/{id}") String update(@PathVariable Long id) { return service.update(id); }
    @GetMapping("/api/admin/purge") String purge() { return service.adminPurge(); }
}
