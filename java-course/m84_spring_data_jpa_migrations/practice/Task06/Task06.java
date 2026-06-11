package m84_spring_data_jpa_migrations.practice.task06;

/**
 * Задача 06 — Модуль 84: аудит (@CreatedDate, @LastModifiedDate, @CreatedBy)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Включите аудит: на классе конфигурации AuditConfig06 поставьте @EnableJpaAuditing
 *      и объявите бин AuditorAware<String> (вернёт, например, "admin").
 *   2) В Product06:
 *        - @EntityListeners(AuditingEntityListener.class) на классе;
 *        - @CreatedDate      Instant createdAt;
 *        - @LastModifiedDate Instant updatedAt;
 *        - @CreatedBy        String createdBy;
 *        - @LastModifiedBy   String updatedBy;
 *      добавьте геттеры.
 *   3) В Runner: сохраните товар (createdAt/createdBy заполнятся), затем обновите цену
 *      (updatedAt/updatedBy обновятся) — выведите все четыре поля до и после.
 *
 * ЦЕЛЬ: получить автоматический аудит «кто/когда создал и менял» без ручного кода.
 *
 * ПОДСКАЗКА: без @EnableJpaAuditing поля останутся null; @CreatedBy берётся из AuditorAware.
 *            createdAt после первого save и после update НЕ меняется, updatedAt — меняется.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.Optional;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
