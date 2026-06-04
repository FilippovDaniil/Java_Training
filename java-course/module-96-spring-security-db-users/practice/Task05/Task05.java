/**
 * Задача 05 — Модуль 96: роли отдельной сущностью (@ManyToMany) — гибкая модель
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, h2.
 *
 * ЗАДАНИЕ:
 *   Вместо enum-набора сделайте роли ОТДЕЛЬНОЙ сущностью (как в реальных проектах).
 *   1) RoleEntity05 (@Entity): id + @Column(unique=true) name ("ROLE_USER", "ROLE_ADMIN").
 *   2) UserEntity05: @ManyToMany(fetch = EAGER) Set<RoleEntity05> roles
 *      (join-таблица users_roles).
 *   3) CustomUserDetailsService05.loadUserByUsername: маппить u.getRoles() в
 *      SimpleGrantedAuthority по role.getName() (имя УЖE с префиксом ROLE_).
 *   4) Засейте роли ROLE_USER/ROLE_ADMIN, пользователя admin с обеими; проверьте доступ
 *      к /api/admin/** (hasRole("ADMIN")).
 *
 * ЦЕЛЬ: освоить нормализованную модель ролей (@ManyToMany) — расширяемую и переиспользуемую.
 *
 * ПОДСКАЗКА: если имя роли уже "ROLE_ADMIN" — НЕ добавляйте префикс повторно; используйте
 *            new SimpleGrantedAuthority(role.getName()).
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

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository05 users, RoleRepository05 roles, PasswordEncoder enc) {
        return args -> {
            RoleEntity05 user = roles.save(new RoleEntity05("ROLE_USER"));
            RoleEntity05 admin = roles.save(new RoleEntity05("ROLE_ADMIN"));
            users.save(new UserEntity05("admin", enc.encode("admin"), Set.of(user, admin)));
        };
    }
}
