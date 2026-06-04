/**
 * Задача 05 — Модуль 82: устранить N+1 через @EntityGraph
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В CategoryRepository05 добавьте derived-метод с @EntityGraph, который жадно
 *      подтянет products БЕЗ написания JPQL:
 *        @EntityGraph(attributePaths = "products")
 *        List<Category05> findByNameContaining(String part);
 *   2) reportGraph(part): @Transactional, вызвать метод и вывести имя + products.size().
 *   3) Сравните лог с Task03/04: @EntityGraph даёт один JOIN-запрос, как JOIN FETCH,
 *      но декларативно поверх derived query.
 *
 * ЦЕЛЬ: освоить @EntityGraph как декларативную альтернативу JOIN FETCH.
 *
 * ПОДСКАЗКА: attributePaths принимает имена ПОЛЕЙ-связей сущности (можно несколько:
 *            {"products", "products.supplier"}). Имя метода — обычный derived query.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
