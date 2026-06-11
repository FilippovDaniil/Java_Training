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

@Service
class TaskService07 {
    // TODO: @PostFilter("filterObject.owner() == authentication.name or hasRole('ADMIN')")
    public List<Task07Dto> findVisible() {
        return List.of(new Task07Dto(1L, "alice", "Задача Алисы"),
                       new Task07Dto(2L, "bob", "Задача Боба"));
    }

    // TODO: @PreAuthorize("hasRole('ADMIN') or @taskSecurity.isOwner(#id, authentication.name)")
    public String update(Long id) { return "задача " + id + " обновлена"; }

    // TODO: @PreAuthorize("hasRole('ADMIN')")
    public String adminPurge() { return "все завершённые задачи удалены"; }
}
