package m81_spring_data_jpa_transactions.practice.task05;

/**
 * Задача 05 — Модуль 81: Propagation.REQUIRES_NEW (аудит переживает откат)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Запись аудита должна сохраняться, даже если основная операция откатилась.
 *   1) AuditService05.log(msg) пометьте @Transactional(propagation = Propagation.REQUIRES_NEW)
 *      — это ОТДЕЛЬНАЯ транзакция, она коммитится независимо от вызывающей.
 *   2) OrderService05.placeOrder(name, fail) пометьте @Transactional:
 *        - audit.log("Попытка заказа: " + name);   // REQUIRES_NEW — сохранится
 *        - orders.save(new Order05(name));
 *        - if (fail) throw new RuntimeException("сбой");   // откатит save, но НЕ аудит
 *   3) В CommandLineRunner: вызовите placeOrder с fail=true (в try/catch),
 *      затем выведите orders.count() (0 — откат) и audits.count() (1 — сохранён).
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему запись аудита уцелела, а заказ — нет?
 *
 * ПОДСКАЗКА: REQUIRES_NEW приостанавливает текущую транзакцию и открывает свою.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
