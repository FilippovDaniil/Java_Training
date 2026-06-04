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
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;
import java.util.UUID;

@RestController
class AttachmentController06 {
    private static final Set<String> ALLOWED = Set.of("image/png", "image/jpeg", "application/pdf");

    @PostMapping("/api/tasks/{id}/attachments")
    String upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        // TODO: if (file.isEmpty()) throw new IllegalArgumentException("Пустой файл");
        // TODO: if (!ALLOWED.contains(file.getContentType()))
        // TODO:     throw new IllegalArgumentException("Недопустимый тип: " + file.getContentType());
        // TODO: String ext = file.getContentType().equals("application/pdf") ? ".pdf" : ".img";
        // TODO: String safeName = UUID.randomUUID() + ext;   // НЕ используем оригинальное имя!
        // TODO: // сохранить file во внешнее хранилище под safeName ...
        // TODO: return "Загружено к задаче " + id + " как " + safeName;
        return null;
    }
}
