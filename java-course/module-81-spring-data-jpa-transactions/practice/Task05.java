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

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@Entity @Table(name = "orders")
class Order05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Order05() {}
    public Order05(String name) { this.name = name; }
}

@Entity @Table(name = "audits")
class Audit05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    protected Audit05() {}
    public Audit05(String message) { this.message = message; }
}

interface OrderRepository05 extends JpaRepository<Order05, Long> {}
interface AuditRepository05 extends JpaRepository<Audit05, Long> {}

@Service
class AuditService05 {
    private final AuditRepository05 repo;
    AuditService05(AuditRepository05 repo) { this.repo = repo; }

    // TODO: @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String msg) {
        repo.save(new Audit05(msg));
    }
}

@Service
class OrderService05 {
    private final OrderRepository05 orders;
    private final AuditService05 audit;
    OrderService05(OrderRepository05 orders, AuditService05 audit) { this.orders = orders; this.audit = audit; }

    // TODO: @Transactional
    public void placeOrder(String name, boolean fail) {
        // TODO: audit.log("Попытка заказа: " + name);
        // TODO: orders.save(new Order05(name));
        // TODO: if (fail) throw new RuntimeException("сбой основной транзакции");
    }
}

@Component
class Runner05 implements CommandLineRunner {
    private final OrderRepository05 orders;
    private final AuditRepository05 audits;
    private final OrderService05 service;
    Runner05(OrderRepository05 o, AuditRepository05 a, OrderService05 s) { this.orders = o; this.audits = a; this.service = s; }

    @Override
    public void run(String... args) {
        try { service.placeOrder("Заказ-1", true); } catch (Exception e) { System.out.println("Откат: " + e.getMessage()); }
        // TODO: System.out.println("Заказов: " + orders.count() + " (ожидается 0)");
        // TODO: System.out.println("Аудитов: " + audits.count() + " (ожидается 1)");
    }
}
