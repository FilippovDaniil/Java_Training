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

@Service
class TaskService04 {
    // taskId -> owner (засеяно: задача 1 принадлежит alice)
    private final Map<Long, String> owners = new ConcurrentHashMap<>(Map.of(1L, "alice"));

    public String updateTask(Long taskId, String currentUser, boolean isAdmin) {
        // TODO: String owner = owners.get(taskId);
        // TODO: if (owner == null) throw new IllegalArgumentException("Нет задачи " + taskId);
        // TODO: if (!isAdmin && !owner.equals(currentUser))
        // TODO:     throw new AccessDeniedException("Не ваша задача");
        // TODO: return "задача " + taskId + " обновлена пользователем " + currentUser;
        return null;
    }
}
