/**
 * Задача 02 — Модуль 94: PasswordEncoder — encode и matches
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Освойте хеширование паролей до интеграции в security.
 *   1) Бин PasswordEncoder = BCryptPasswordEncoder.
 *   2) В CommandLineRunner:
 *        - hash1 = encoder.encode("secret"); вывести (увидите $2a$10$...);
 *        - hash2 = encoder.encode("secret"); вывести — он ОТЛИЧАЕТСЯ от hash1 (разная соль),
 *          но encoder.matches("secret", hash1) и matches("secret", hash2) → оба true;
 *        - encoder.matches("wrong", hash1) → false.
 *   3) Выведите выводы: один пароль → разные хеши (соль), matches сравнивает корректно.
 *
 * ЦЕЛЬ: понять, что хеш необратим, содержит соль, а matches — это повторное хеширование.
 *
 * ПОДСКАЗКА: НИКОГДА не сравнивайте пароли через equals хешей — только encoder.matches.
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // TODO: return new BCryptPasswordEncoder();
        return null;
    }

    @Bean
    CommandLineRunner demo(PasswordEncoder encoder) {
        return args -> {
            // TODO: String h1 = encoder.encode("secret");
            // TODO: String h2 = encoder.encode("secret");
            // TODO: System.out.println("h1 = " + h1);
            // TODO: System.out.println("h2 = " + h2);
            // TODO: System.out.println("h1 == h2 (строки)? " + h1.equals(h2));      // false (разная соль)
            // TODO: System.out.println("matches secret/h1? " + encoder.matches("secret", h1)); // true
            // TODO: System.out.println("matches secret/h2? " + encoder.matches("secret", h2)); // true
            // TODO: System.out.println("matches wrong/h1?  " + encoder.matches("wrong", h1));   // false
        };
    }
}
