/**
 * Задача 04 — Модуль 84: оптимистичная блокировка — поле @Version
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В Product04 добавьте поле версии:
 *        @Version
 *        private long version;
 *      и геттер getVersion().
 *   2) priceUpdate(id, newPrice): @Transactional — загрузить товар, setPrice (dirty checking).
 *   3) В CommandLineRunner: сохраните товар (version=0 после INSERT), затем дважды
 *      обновите цену через сервис; после каждого обновления перечитайте товар и
 *      выведите version — она должна расти: 0 → 1 → 2.
 *
 * ЦЕЛЬ: увидеть, что Hibernate сам инкрементит @Version при каждом UPDATE
 *       (UPDATE ... SET version = version + 1 WHERE id=? AND version=?).
 *
 * ПОДСКАЗКА: версией управляет Hibernate — НЕ присваивайте её вручную.
 *            Перечитывать товар удобнее в отдельном @Transactional-методе.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
