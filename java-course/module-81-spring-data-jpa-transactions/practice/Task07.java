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

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// ============================================================
// Сущности
// ============================================================

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    protected Product07() {}
    public Product07(String name, int stock) { this.name = name; this.stock = stock; }
    public Long getId() { return id; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}

@Entity @Table(name = "orders")
class Order07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int qty;
    protected Order07() {}
    public Order07(Long productId, int qty) { this.productId = productId; this.qty = qty; }
}

@Entity @Table(name = "audits")
class Audit07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    protected Audit07() {}
    public Audit07(String message) { this.message = message; }
}

interface ProductRepository07 extends JpaRepository<Product07, Long> {}
interface OrderRepository07 extends JpaRepository<Order07, Long> {}
interface AuditRepository07 extends JpaRepository<Audit07, Long> {}

// ============================================================
// Сервисы (каркасы)
// ============================================================

// TODO: @Service
class AuditService07 {
    private final AuditRepository07 repo;
    AuditService07(AuditRepository07 repo) { this.repo = repo; }

    // TODO: @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String msg) {
        // TODO: repo.save(new Audit07(msg));
    }
}

// TODO: @Service
class OrderService07 {
    private final ProductRepository07 products;
    private final OrderRepository07 orders;
    private final AuditService07 audit;
    OrderService07(ProductRepository07 p, OrderRepository07 o, AuditService07 a) {
        this.products = p; this.orders = o; this.audit = a;
    }

    // TODO: @Transactional
    public Order07 placeOrder(Long productId, int qty) {
        // TODO: audit.log("Заказ товара " + productId + " x" + qty);
        // TODO: Product07 p = products.findById(productId).orElseThrow();
        // TODO: if (p.getStock() < qty) throw new IllegalStateException("Недостаточно на складе");
        // TODO: p.setStock(p.getStock() - qty);              // dirty checking
        // TODO: return orders.save(new Order07(productId, qty));
        return null;
    }
}

// ============================================================
// Runner (каркас)
// ============================================================

// TODO: @Component
class ShopRunner07 implements CommandLineRunner {
    private final ProductRepository07 products;
    private final OrderRepository07 orders;
    private final AuditRepository07 audits;
    private final OrderService07 service;
    ShopRunner07(ProductRepository07 p, OrderRepository07 o, AuditRepository07 a, OrderService07 s) {
        this.products = p; this.orders = o; this.audits = a; this.service = s;
    }

    @Override
    public void run(String... args) {
        Product07 laptop = products.save(new Product07("Ноутбук", 2));
        // TODO: успешный заказ: service.placeOrder(laptop.getId(), 1);
        // TODO: неуспешный: try { service.placeOrder(laptop.getId(), 5); } catch (Exception e) { ... }
        // TODO: выведите остаток (products.findById...getStock()), orders.count(), audits.count()
        //       Ожидается: остаток=1, заказов=1, аудитов=2
    }
}
