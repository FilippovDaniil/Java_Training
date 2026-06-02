/**
 * Задача 04 — Модуль 53: @MappedSuperclass — общий технический суперкласс
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Реализуйте паттерн «технического базового класса» через @MappedSuperclass:
 *     1) BaseEntity — @MappedSuperclass, поля:
 *        - id: Long, @Id @GeneratedValue(IDENTITY)
 *        - createdAt: LocalDateTime, инициализируется LocalDateTime.now()
 *        - updatedAt: LocalDateTime, @PreUpdate обновляет значение
 *     2) Customer — @Entity @Table("customers"), поля: name, email.
 *        Наследует BaseEntity.
 *     3) Order — @Entity @Table("orders"), поля: totalAmount (BigDecimal), status (String).
 *        Наследует BaseEntity. Добавьте связь @ManyToOne на Customer.
 *     4) В main() сохраните Customer и несколько Orders,
 *        затем загрузите Customer и выведите его id и createdAt.
 *     5) Убедитесь (через show_sql=true), что таблицы customers и orders независимы —
 *        нет таблицы base_entity.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Клиент id=1, создан: 2026-06-02T...
 *   Заказ id=1, сумма: 5000.00, статус: NEW
 *
 * ПОДСКАЗКА:
 *   @MappedSuperclass — это НЕ @Entity, поэтому:
 *   - нельзя делать session.get(BaseEntity.class, 1L) — выбросит исключение;
 *   - нельзя писать JPQL "FROM BaseEntity";
 *   - но можно session.get(Customer.class, 1L) — работает нормально.
 *
 *   @PreUpdate — колбэк JPA, вызывается автоматически перед UPDATE:
 *     @PreUpdate
 *     void onUpdate() { this.updatedAt = LocalDateTime.now(); }
 */
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Task04 {

    public static void main(String[] args) {
        // TODO: создайте SessionFactory; зарегистрируйте Customer и Order
        //       (BaseEntity регистрировать НЕ нужно — она не @Entity)

        // TODO: сохраните Customer("Алексей Иванов", "alex@example.com")

        // TODO: сохраните два Order, оба связанные с этим Customer

        // TODO: загрузите Customer по id; выведите id и createdAt

        // TODO: выведите все Orders этого Customer

        // TODO: убедитесь через show_sql, что таблицы customers и orders независимы

        // TODO: попробуйте (в отдельном try-catch) сделать
        //       session.get(BaseAuditEntity.class, 1L) — что произойдёт?
    }
}

// --- @MappedSuperclass (дополните) ---

@MappedSuperclass
abstract class BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    void onUpdate() {
        // TODO: установите updatedAt = LocalDateTime.now()
    }

    // TODO: геттеры id, createdAt, updatedAt
}

// --- Customer (дополните) ---

@Entity
@Table(name = "customers")
class Customer extends BaseAuditEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // TODO: конструктор(name, email), геттеры, toString()
}

// --- Order (дополните) ---

@Entity
@Table(name = "orders")
class Order extends BaseAuditEntity {

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // TODO: конструктор(totalAmount, status, customer), геттеры, toString()
}
