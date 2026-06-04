/**
 * Задача 02 — Модуль 80: Native query (nativeQuery = true)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте «сырой» SQL-запрос (по ТАБЛИЦЕ и КОЛОНКАМ, а не сущности):
 *     1) @Query(value = "SELECT * FROM products WHERE price > :min", nativeQuery = true)
 *        List<Product02> nativeExpensive(@Param("min") long min);
 *     2) Подсчёт средней цены через native:
 *        @Query(value = "SELECT AVG(price) FROM products", nativeQuery = true)
 *        Double averagePrice();
 *   Проверьте в CommandLineRunner.
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем native-запрос отличается от JPQL? В чём минус native при смене БД?
 *
 * ПОДСКАЗКА: native использует имена ТАБЛИЦ (products) и КОЛОНОК (price), не сущностей.
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

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
