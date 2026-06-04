/**
 * Задача 01 — Модуль 80: @Query (JPQL) с именованными параметрами
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в репозиторий JPQL-запросы через @Query:
 *     1) expensiveInCategory(min, cat):
 *          @Query("SELECT p FROM Product01 p WHERE p.price > :min AND p.category = :cat")
 *          параметры через @Param("min"), @Param("cat")
 *     2) searchByName(part):
 *          @Query("SELECT p FROM Product01 p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :part, '%'))")
 *   Проверьте в CommandLineRunner.
 *
 * ВНИМАНИЕ: в JPQL пишут ИМЯ КЛАССА (Product01) и ПОЛЯ (p.price), а не таблицы/колонки.
 *
 * ПОДСКАЗКА: import org.springframework.data.jpa.repository.Query;
 *            import org.springframework.data.repository.query.Param;
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
