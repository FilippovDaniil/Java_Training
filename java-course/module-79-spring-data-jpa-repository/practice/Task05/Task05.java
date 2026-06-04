/**
 * Задача 05 — Модуль 79: Top/First и сортировка через Sort
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Добавьте в репозиторий:
 *        - findTop3ByOrderByPriceDesc()       → List<Product05> (топ-3 дорогих)
 *        - findFirstByOrderByPriceAsc()        → Optional<Product05> (самый дешёвый)
 *   2) В CommandLineRunner используйте также готовый findAll(Sort):
 *        repo.findAll(Sort.by("price").descending())  — все, по убыванию цены.
 *   3) Выведите результаты.
 *
 * ЦЕЛЬ: ограничение количества (Top/First) и динамическая сортировка (Sort).
 *
 * ПОДСКАЗКА:
 *   import org.springframework.data.domain.Sort;
 *   Top<N> в имени метода ограничивает выборку; Sort передаётся аргументом.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
