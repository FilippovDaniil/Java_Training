package m97_spring_security_authorization.practice.task07;

/**
 * Задача 07 — Модуль 97: МИНИ-ПРОЕКТ «Полная авторизация Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЦЕЛЬ: собрать многоуровневую авторизацию Task Tracker: ADMIN управляет всеми задачами,
 *       обычный пользователь — только своими. Скомбинировать request-level + method security
 *       + ownership.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) @EnableMethodSecurity + SecurityFilterChain07:
 *        - "/api/admin/**" → hasRole("ADMIN") (request-level, грубое правило раздела);
 *        - "/api/**" → authenticated; httpBasic; csrf.disable.
 *
 *   2) Хранилище задач (Map id -> owner), бин @Component("taskSecurity") TaskSecurity07.isOwner.
 *
 *   3) TaskService07 (method security):
 *        - @PostFilter("filterObject.owner() == authentication.name or hasRole('ADMIN')")
 *          List<Task07Dto> findVisible();                 // список: свои или все (ADMIN)
 *        - @PreAuthorize("hasRole('ADMIN') or @taskSecurity.isOwner(#id, authentication.name)")
 *          String update(Long id);                        // менять: свою или ADMIN
 *        - @PreAuthorize("hasRole('ADMIN')") String adminPurge();   // только ADMIN
 *
 *   4) Контроллеры:
 *        - GET /api/tasks → findVisible();
 *        - PUT /api/tasks/{id} → update(id);
 *        - GET /api/admin/purge → adminPurge().
 *
 *   5) Пользователи: alice(USER, владеет задачей 1), bob(USER), admin(ADMIN, USER).
 *
 * АРХИТЕКТУРА (три уровня контроля):
 *   request-level → раздел /api/admin/** только ADMIN
 *   method @PreAuthorize → конкретная операция (роль/ownership)
 *   @PostFilter → видимость элементов списка по владельцу
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - alice: GET /api/tasks → только свои; PUT /1 → 200; PUT /2(боба) → 403; /api/admin/purge → 403;
 *   - admin: видит все задачи, PUT любой → 200, /api/admin/purge → 200.
 *
 * ПОДСКАЗКА: соберите Task01 (request-level), Task02/03 (method security/SpEL),
 *            Task05 (бин-ownership), Task06 (@PostFilter).
 */

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

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
