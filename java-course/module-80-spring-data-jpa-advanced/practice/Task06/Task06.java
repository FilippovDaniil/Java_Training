/**
 * Задача 06 — Модуль 80: Specification — динамический фильтр
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Постройте запрос динамически из НЕОБЯЗАТЕЛЬНЫХ условий.
 *   1) Репозиторий наследует ещё и JpaSpecificationExecutor<Product06>.
 *   2) В ProductSpecs06 реализуйте две спецификации (возвращают null, если параметр null):
 *        hasCategory(category): cb.equal(root.get("category"), category)
 *        priceAtLeast(min):     cb.greaterThanOrEqualTo(root.get("price"), min)
 *   3) В сервисе search(category, min) соберите:
 *        Specification.where(hasCategory(category)).and(priceAtLeast(min))
 *      и верните repo.findAll(spec).
 *   4) В CommandLineRunner вызовите search с разными комбинациями (оба заданы,
 *      только категория, только цена, ничего) и выведите размеры результатов.
 *
 * ЦЕЛЬ: один метод поиска покрывает любой набор фильтров (null → условие не применяется).
 *
 * ПОДСКАЗКА:
 *   import org.springframework.data.jpa.domain.Specification;
 *   import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
