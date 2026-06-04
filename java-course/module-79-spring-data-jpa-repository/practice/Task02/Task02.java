/**
 * Задача 02 — Модуль 79: Derived queries — простые
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в ProductRepository02 методы-запросы (тело писать НЕ нужно — только сигнатуры):
 *     - findByCategory(String category)            → List<Product02>
 *     - findByPriceLessThan(long price)            → List<Product02>
 *     - findBySku(String sku)                      → Optional<Product02>  (0 или 1)
 *   В CommandLineRunner засейте данные и проверьте каждый метод (выведите результаты).
 *
 * ЦЕЛЬ: понять, что имя метода = запрос (Spring сам генерирует SQL).
 *
 * ПОДСКАЗКА:
 *   Имя поля в методе должно ТОЧНО совпадать с полем сущности (category, price, sku).
 *   Для «ноль или один» используйте Optional.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
