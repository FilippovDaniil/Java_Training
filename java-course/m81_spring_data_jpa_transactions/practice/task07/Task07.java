package m81_spring_data_jpa_transactions.practice.task07;

/**
 * Задача 07 — Модуль 81: МИНИ-ПРОЕКТ «Транзакционное оформление заказа shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЦЕЛЬ: реализовать атомарное оформление заказа: списать товар со склада + создать
 *       заказ + записать аудит — с корректным откатом при нехватке остатка
 *       и независимым сохранением аудита.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Сущности: Product07(id, name, stock), Order07(id, productId, qty), Audit07(id, message).
 *   Репозитории: ProductRepository07, OrderRepository07, AuditRepository07.
 *
 *   1) AuditService07.log(msg): @Transactional(propagation = REQUIRES_NEW)
 *        — аудит сохраняется независимо (даже при откате заказа).
 *
 *   2) OrderService07.placeOrder(productId, qty): @Transactional
 *        a) audit.log("Заказ товара " + productId + " x" + qty);   // переживёт откат
 *        b) Product07 p = products.findById(productId).orElseThrow();
 *        c) if (p.getStock() < qty) throw new IllegalStateException("Недостаточно на складе"); // откат
 *        d) p.setStock(p.getStock() - qty);     // dirty checking — без save()!
 *        e) return orders.save(new Order07(productId, qty));
 *
 *   3) CommandLineRunner:
 *        - засейте товар "Ноутбук" со stock=2;
 *        - успешный заказ qty=1 → остаток 1, заказ создан;
 *        - неуспешный заказ qty=5 (в try/catch) → IllegalStateException, остаток НЕ изменился,
 *          заказ НЕ создан, но запись аудита есть;
 *        - выведите: остаток товара, orders.count(), audits.count().
 *
 * АРХИТЕКТУРА:
 *
 *   placeOrder() [@Transactional]
 *      ├─ audit.log() [REQUIRES_NEW]  → отдельная транзакция (сохранится всегда)
 *      ├─ списать stock (dirty checking)
 *      └─ создать Order
 *   Нехватка остатка → откат stock и Order (но НЕ аудита).
 *
 * ОЖИДАЕМЫЙ ИТОГ после двух заказов (1 успех, 1 провал):
 *   остаток = 1, заказов = 1, аудитов = 2.
 *
 * ПОДСКАЗКА: соберите вместе Task01 (атомарность), Task02 (dirty checking), Task05 (REQUIRES_NEW).
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

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
