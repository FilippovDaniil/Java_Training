/**
 * Задача 01 — Модуль 96: сущность User и UserRepository
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa,
 *   com.h2database:h2 (см. theory.md). bare-javac не компилируется.
 *
 * ЗАДАНИЕ:
 *   1) Доработайте сущность UserEntity01:
 *        - @Column(unique = true, nullable = false) username;
 *        - @Column(nullable = false) password (под хеш!);
 *        - boolean enabled = true.
 *   2) В UserRepository01 добавьте методы:
 *        Optional<UserEntity01> findByUsername(String username);
 *        boolean existsByUsername(String username);
 *   3) В CommandLineRunner: сохраните пользователя и найдите его по username,
 *      проверьте existsByUsername.
 *
 * ЦЕЛЬ: подготовить хранилище пользователей — основу для CustomUserDetailsService (Task02).
 *
 * ПОДСКАЗКА: уникальный индекс на username защищает от дублей на уровне БД.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }

    @org.springframework.context.annotation.Bean
    CommandLineRunner demo(UserRepository01 repo) {
        return args -> {
            UserEntity01 u = new UserEntity01("alice", "{hash}");
            repo.save(u);
            // TODO: System.out.println("найден: " + repo.findByUsername("alice").isPresent());   // true
            // TODO: System.out.println("есть alice? " + repo.existsByUsername("alice"));          // true
            // TODO: System.out.println("есть bob? " + repo.existsByUsername("bob"));              // false
        };
    }
}
