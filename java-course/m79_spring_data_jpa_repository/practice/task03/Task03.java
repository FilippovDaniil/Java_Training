package m79_spring_data_jpa_repository.practice.task03;

/**
 * Задача 03 — Модуль 79: Derived queries — And/Or/Between/Like/OrderBy
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в ProductRepository03 более сложные derived-методы:
 *     - findByCategoryAndPriceGreaterThan(String category, long price)
 *     - findByPriceBetween(long min, long max)
 *     - findByNameContainingIgnoreCase(String part)       // подстрока без учёта регистра
 *     - findByCategoryOrderByPriceDesc(String category)   // сортировка в имени
 *   Проверьте каждый в CommandLineRunner.
 *
 * ЦЕЛЬ: освоить ключевые слова derived-запросов (см. таблицу в theory.md).
 *
 * ПОДСКАЗКА:
 *   Between — диапазон; Containing — LIKE %...%; IgnoreCase — без регистра;
 *   OrderBy<Поле>Desc — сортировка.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
