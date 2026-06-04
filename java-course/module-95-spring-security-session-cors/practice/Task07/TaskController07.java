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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    private static final Set<String> ALLOWED = Set.of("image/png", "image/jpeg", "application/pdf");

    @GetMapping
    String myTasks(Principal principal) {
        // TODO: return principal.getName() + ": " + List.of("Задача 1", "Задача 2");
        return null;
    }

    @PostMapping("/{id}/attachments")
    String upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        // TODO: проверить непустоту + ALLOWED.contains(file.getContentType());
        // TODO: String safeName = UUID.randomUUID() + ".bin";   // не из оригинального имени
        // TODO: return "к задаче " + id + " загружено " + safeName;
        return null;
    }
}
