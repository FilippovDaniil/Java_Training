package m53_hibernate_inheritance.practice.task04;

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
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:shop;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Customer.class);
        config.addAnnotatedClass(Order.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== @MappedSuperclass — ТЕХНИЧЕСКИЙ БАЗОВЫЙ КЛАСС ===\n");

            Long customerId = null;

            // ===== СОХРАНЕНИЕ =====
            System.out.println("--- СОХРАНЕНИЕ КЛИЕНТА И ЗАКАЗОВ ---");

            try (Session session = factory.openSession()) {
                session.getTransaction().begin();

                Customer customer = new Customer("Алексей Иванов", "alex@example.com");
                session.persist(customer);
                customerId = customer.getId();

                Order order1 = new Order(
                        new BigDecimal("5000.00"),
                        "NEW",
                        customer
                );

                Order order2 = new Order(
                        new BigDecimal("12000.00"),
                        "PROCESSING",
                        customer
                );

                session.persist(order1);
                session.persist(order2);

                System.out.println("   ✅ Сохранен клиент: " + customer);
                System.out.println("   ✅ Сохранен заказ 1: " + order1);
                System.out.println("   ✅ Сохранен заказ 2: " + order2);

                session.getTransaction().commit();
            }

            // ===== ЗАГРУЗКА КЛИЕНТА =====
            System.out.println("\n--- ЗАГРУЗКА КЛИЕНТА ---");

            try (Session session = factory.openSession()) {
                Customer loadedCustomer = session.get(Customer.class, customerId);
                if (loadedCustomer != null) {
                    System.out.println("   👤 Клиент:");
                    System.out.println("      id: " + loadedCustomer.getId());
                    System.out.println("      name: " + loadedCustomer.getName());
                    System.out.println("      email: " + loadedCustomer.getEmail());
                    System.out.println("      created_at: " + loadedCustomer.getCreatedAt());
                    System.out.println("      updated_at: " + loadedCustomer.getUpdatedAt());
                }
            }

            // ===== ЗАГРУЗКА ЗАКАЗОВ КЛИЕНТА =====
            System.out.println("\n--- ЗАГРУЗКА ЗАКАЗОВ КЛИЕНТА ---");

            try (Session session = factory.openSession()) {
                Customer customerWithOrders = session.createQuery(
                                "SELECT DISTINCT c FROM Customer c " +
                                        "LEFT JOIN FETCH c.orders " +
                                        "WHERE c.id = :id",
                                Customer.class)
                        .setParameter("id", customerId)
                        .getSingleResult();

                System.out.println("   👤 Клиент: " + customerWithOrders.getName());
                System.out.println("   📦 Заказы:");
                for (Order order : customerWithOrders.getOrders()) {
                    System.out.println("      - id: " + order.getId());
                    System.out.println("        сумма: " + order.getTotalAmount());
                    System.out.println("        статус: " + order.getStatus());
                    System.out.println("        создан: " + order.getCreatedAt());
                    System.out.println("        обновлен: " + order.getUpdatedAt());
                }
            }

            // ===== ПРОВЕРКА СТРУКТУРЫ ТАБЛИЦ =====
            System.out.println("\n--- ПРОВЕРКА СТРУКТУРЫ ТАБЛИЦ ---");

            try (Session session = factory.openSession()) {
                Number customersCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM customers")
                        .getSingleResult();
                System.out.println("   📊 Строк в customers: " + customersCount);

                Number ordersCount = (Number) session
                        .createNativeQuery("SELECT COUNT(*) FROM orders")
                        .getSingleResult();
                System.out.println("   📊 Строк в orders: " + ordersCount);

                System.out.println("\n   📌 В таблице customers есть поля: id, name, email, created_at, updated_at");
                System.out.println("   📌 В таблице orders есть поля: id, total_amount, status, customer_id, created_at, updated_at");
                System.out.println("   📌 Нет таблицы base_entity (MappedSuperclass не создает таблицу)");
            }

            // ===== ПОПЫТКА ЗАГРУЗИТЬ BaseAuditEntity (ЗАКОММЕНТИРОВАНА) =====
            System.out.println("\n--- ПОПЫТКА ЗАГРУЗИТЬ BaseAuditEntity ---");
            System.out.println("   📌 BaseAuditEntity - это @MappedSuperclass, а НЕ @Entity");
            System.out.println("   📌 Нельзя использовать session.get(BaseAuditEntity.class, id)");
            System.out.println("   📌 Попытка вызовет UnknownEntityTypeException");

            // ❌ Этот код ЗАКОММЕНТИРОВАН, потому что вызовет исключение
            /*
            try (Session session = factory.openSession()) {
                try {
                    BaseAuditEntity entity = session.get(BaseAuditEntity.class, 1L);
                    System.out.println("   ❌ Не должно сработать!");
                } catch (IllegalArgumentException e) {
                    System.out.println("   ✅ Ожидаемое исключение: " + e.getClass().getSimpleName());
                    System.out.println("   ✅ Сообщение: " + e.getMessage());
                }
            }
            */

            System.out.println("\n--- ВСЕ ЗАКАЗЫ ---");

            try (Session session = factory.openSession()) {
                List<Order> allOrders = session
                        .createQuery("FROM Order ORDER BY id", Order.class)
                        .getResultList();

                System.out.println("   Всего заказов: " + allOrders.size());
                for (Order order : allOrders) {
                    System.out.println("   - " + order);
                }
            }

            System.out.println("\n--- ВЫВОДЫ ---");
            System.out.println("   📌 @MappedSuperclass используется для общих полей");
            System.out.println("   📌 Поля наследуются, но таблица не создается");
            System.out.println("   📌 createdAt и updatedAt автоматически заполняются");
            System.out.println("   📌 @PreUpdate вызывается перед UPDATE");
            System.out.println("   📌 Нельзя использовать как сущность (session.get)");
            System.out.println("   📌 Нельзя использовать в JPQL (FROM BaseEntity)");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
