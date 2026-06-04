/**
 * Задача 03 — Модуль 77: CRUD-цикл через репозиторий
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Продемонстрируйте полный CRUD-цикл в CommandLineRunner:
 *     1) CREATE: сохраните товар, запомните присвоенный id (saved.getId()).
 *     2) READ:   найдите по id через repo.findById(id) → Optional; выведите имя.
 *     3) UPDATE: измените цену найденного товара (setPrice) и снова repo.save(...).
 *     4) DELETE: repo.deleteById(id); выведите repo.existsById(id) (должно быть false).
 *
 *   Включите spring.jpa.show-sql=true и наблюдайте INSERT/SELECT/UPDATE/DELETE в логах.
 *
 * ЦЕЛЬ: увидеть соответствие методов репозитория и SQL-операций.
 *
 * ПОДСКАЗКА:
 *   findById возвращает Optional — используйте orElseThrow() или ifPresent(...).
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
