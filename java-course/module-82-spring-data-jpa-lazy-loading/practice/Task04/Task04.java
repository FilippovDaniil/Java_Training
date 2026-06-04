/**
 * Задача 04 — Модуль 82: устранить N+1 через JOIN FETCH
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *   spring.jpa.show-sql=true — чтобы убедиться, что запрос теперь ОДИН.
 *
 * ЗАДАНИЕ:
 *   1) В CategoryRepository04 добавьте метод с @Query и JOIN FETCH:
 *        @Query("select distinct c from Category04 c join fetch c.products")
 *        List<Category04> findAllWithProducts();
 *   2) reportFetch(): @Transactional, вызвать findAllWithProducts() и вывести
 *      имя + размер products каждой категории.
 *   3) Сравните лог с Task03: теперь должен быть ОДИН SELECT с JOIN (без N+1).
 *
 * ЦЕЛЬ: освоить JOIN FETCH как основной приём против N+1.
 *
 * ПОДСКАЗКА: distinct убирает дубли корня (JOIN размножает строки категории
 *            по числу её товаров). Имя сущности в JPQL — Category04, не имя таблицы.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
