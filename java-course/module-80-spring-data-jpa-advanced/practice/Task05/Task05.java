/**
 * Задача 05 — Модуль 80: @Modifying — массовое обновление
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Поднимите цены на категорию одним запросом (bulk-update).
 *   1) В репозитории:
 *        @Modifying
 *        @Query("UPDATE Product05 p SET p.price = p.price * :factor WHERE p.category = :cat")
 *        int raisePrices(@Param("factor") double factor, @Param("cat") String category);
 *      (возвращает число изменённых строк)
 *   2) PriceService05 (@Service): метод raise(cat, factor) пометьте @Transactional
 *      и вызовите repo.raisePrices(...).
 *   3) В CommandLineRunner: засейте товары, вызовите raise("Электроника", 1.1),
 *      выведите число изменённых строк и новые цены.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему после bulk-update уже загруженные в кэш объекты могут «не знать» новых цен?
 *
 * ПОДСКАЗКА: @Modifying-запрос требует @Transactional; bulk обходит persistence context.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
