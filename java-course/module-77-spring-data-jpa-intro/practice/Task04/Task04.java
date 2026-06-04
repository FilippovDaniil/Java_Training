/**
 * Задача 04 — Модуль 77: EntityManager напрямую (что под капотом Spring Data)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Spring Data — обёртка над EntityManager. Поработайте с ним напрямую.
 *   1) В ProductDao04 (@Repository) внедрите EntityManager через @PersistenceContext.
 *   2) Метод save(Product04 p): пометьте @Transactional, вызовите em.persist(p).
 *   3) Метод find(Long id): верните em.find(Product04.class, id).
 *   4) В CommandLineRunner вызовите dao.save(...) и dao.find(...), выведите имя.
 *
 * ЦЕЛЬ: понять, что любой репозиторий в итоге работает через EntityManager.
 *
 * ПОДСКАЗКА:
 *   import jakarta.persistence.PersistenceContext; import jakarta.persistence.EntityManager;
 *   import org.springframework.transaction.annotation.Transactional;
 *   persist требует активной транзакции — отсюда @Transactional на save.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
