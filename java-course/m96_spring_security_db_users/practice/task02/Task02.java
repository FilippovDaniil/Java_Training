package m96_spring_security_db_users.practice.task02;

/**
 * Задача 02 — Модуль 96: CustomUserDetailsService (загрузка пользователя из БД)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, h2.
 *
 * ЗАДАНИЕ:
 *   1) Реализуйте CustomUserDetailsService02 implements UserDetailsService:
 *        loadUserByUsername(username):
 *          UserEntity02 u = repo.findByUsername(username)
 *              .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
 *          return User.withUsername(u.getUsername())
 *              .password(u.getPassword())
 *              .authorities("ROLE_USER")           // роли разберём в Task03/05
 *              .disabled(!u.isEnabled())
 *              .build();
 *   2) Бин PasswordEncoder (BCrypt). При старте засейте одного пользователя
 *      (password = encoder.encode("password")).
 *   3) SecurityFilterChain: всё authenticated, httpBasic, csrf.disable.
 *   4) Проверьте: вход alice/password (Basic) на GET /api/tasks → 200; неверный пароль → 401.
 *
 * ЦЕЛЬ: связать таблицу users с механизмом аутентификации через UserDetailsService.
 *
 * ПОДСКАЗКА: достаточно бинов UserDetailsService + PasswordEncoder — Spring сам соберёт
 *            DaoAuthenticationProvider.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository02 repo, PasswordEncoder enc) {
        return args -> repo.save(new UserEntity02("alice", enc.encode("password")));
    }
}
