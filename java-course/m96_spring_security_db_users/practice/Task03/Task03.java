package m96_spring_security_db_users.practice.task03;

/**
 * Задача 03 — Модуль 96: роли из БД → GrantedAuthority (префикс ROLE_)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, h2.
 *
 * ЗАДАНИЕ:
 *   Пользователь хранит набор ролей; преобразуйте их в authorities с префиксом ROLE_.
 *   1) UserEntity03 содержит @ElementCollection @Enumerated(STRING) Set<Role03> roles
 *      (Role03 — enum {USER, ADMIN}), fetch = EAGER (роли нужны при логине).
 *   2) В CustomUserDetailsService03.loadUserByUsername:
 *        List<GrantedAuthority> auth = u.getRoles().stream()
 *            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
 *            .collect(Collectors.toList());
 *        return User.withUsername(...).password(...).authorities(auth).build();
 *   3) Засейте alice(USER) и admin(USER, ADMIN). SecurityFilterChain:
 *        "/api/admin/**" hasRole("ADMIN"); остальное authenticated; httpBasic; csrf.disable.
 *   4) Проверьте: admin → /api/admin/stats 200; alice → 403.
 *
 * ЦЕЛЬ: корректно отобразить роли из БД в authorities (ROLE_-префикс — обязателен для hasRole).
 *
 * ПОДСКАЗКА: EAGER для ролей избавит от LazyInitializationException при загрузке UserDetails.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository03 repo, PasswordEncoder enc) {
        return args -> {
            repo.save(new UserEntity03("alice", enc.encode("password"), Set.of(Role03.USER)));
            repo.save(new UserEntity03("admin", enc.encode("admin"), Set.of(Role03.USER, Role03.ADMIN)));
        };
    }
}
