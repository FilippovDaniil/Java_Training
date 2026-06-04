/**
 * Задача 06 — Модуль 97: @PostFilter — фильтрация возвращаемой коллекции
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Метод возвращает ВСЕ задачи, но пользователь должен видеть только свои (ADMIN — все).
 *   1) Task06Dto (record): Long id, String owner, String title.
 *   2) В TaskService06:
 *        @PostFilter("filterObject.owner() == authentication.name or hasRole('ADMIN')")
 *        public List<Task06Dto> findAll() { ... возвращает задачи разных владельцев ... }
 *      (@PostFilter отсеет чужие ПОСЛЕ выполнения метода.)
 *   3) @EnableMethodSecurity; контроллер GET /api/tasks → сервис.
 *   4) Проверьте: alice видит только свои; admin — все.
 *   5) В комментарии: @PostFilter грузит ВСЁ, затем фильтрует — для больших выборок лучше
 *      фильтровать в запросе (findByOwner). Используйте точечно.
 *
 * ЦЕЛЬ: освоить @PostFilter и понять его ограничение по производительности.
 *
 * ПОДСКАЗКА: filterObject — текущий элемент коллекции; для record-поля owner используйте owner().
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostFilter;
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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
