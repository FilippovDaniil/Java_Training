/**
 * Задача 04 — Модуль 96: сервис регистрации (хеширование, уникальность, роль по умолчанию)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, h2.
 *
 * ЗАДАНИЕ:
 *   1) RegistrationService04.register(username, rawPassword): @Transactional
 *        - если repo.existsByUsername(username) → throw IllegalStateException (имя занято);
 *        - сохранить UserEntity04 с password = encoder.encode(rawPassword) (ХЕШ!),
 *          ролью USER по умолчанию, enabled = true;
 *        - вернуть сохранённого пользователя.
 *   2) В CommandLineRunner: зарегистрируйте "alice"/"password"; убедитесь, что в БД
 *      пароль — это BCrypt-хеш (начинается с $2a$), а не "password".
 *   3) Повторная регистрация "alice" → IllegalStateException (поймать, напечатать).
 *
 * ЦЕЛЬ: освоить корректную регистрацию: хеш пароля + защита от дублей + дефолтная роль.
 *
 * ПОДСКАЗКА: НИКОГДА не сохраняйте сырой пароль; роль ADMIN новому пользователю не давать.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    CommandLineRunner demo(RegistrationService04 service, UserRepository04 repo) {
        return args -> {
            // TODO: service.register("alice", "password");
            // TODO: System.out.println("хеш в БД: " + repo.findByUsername("alice").get().getPassword());
            // TODO: try { service.register("alice", "other"); }
            // TODO: catch (IllegalStateException e) { System.out.println("повтор: " + e.getMessage()); }
        };
    }
}
