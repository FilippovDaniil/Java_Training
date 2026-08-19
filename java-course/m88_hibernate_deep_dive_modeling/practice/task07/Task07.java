package m88_hibernate_deep_dive_modeling.practice.task07;

/**
 * Задача 07 — Модуль 88: МИНИ-ПРОЕКТ «Правильно смоделированный домен shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЦЕЛЬ: собрать корректную доменную модель, применив ВСЕ приёмы модуля:
 *       суррогатный id + бизнес-ключ, value object, составной ключ.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Money07 — @Embeddable value object (amount копейки + currency), equals/hashCode по значению.
 *
 *   2) Product07 — @Entity:
 *        - @Id @GeneratedValue Long id (суррогатный PK);
 *        - @NaturalId(mutable = false) String sku (бизнес-ключ);
 *        - String name; @Embedded Money07 price;
 *        - equals/hashCode ПО sku.
 *
 *   3) OrderLineId07 — @Embeddable Serializable составной ключ (Long orderId, Long productId),
 *      equals/hashCode по обоим.
 *
 *   4) OrderLine07 — @Entity:
 *        - @EmbeddedId OrderLineId07 id;
 *        - int qty; @Embedded Money07 lineTotal;
 *
 *   СЦЕНАРИЙ в main:
 *     a) сохраните 2 товара (sku "SKU-1","SKU-2") с ценами Money07;
 *     b) положите оба Product07 в HashSet — set.size() == 2, set.contains(перечитанного) == true
 *        (equals по sku стабилен);
 *     c) найдите товар по @NaturalId (session.bySimpleNaturalId);
 *     d) сохраните 2 строки заказа с составными ключами; найдите одну по new OrderLineId07(...).
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - HashSet корректно содержит товары до и после persist;
 *   - поиск по sku и по составному ключу возвращает нужные объекты;
 *   - Money07 равны по значению.
 *
 * ПОДСКАЗКА: соберите Task01 (бизнес-ключ), Task02 (Money @Embeddable),
 *            Task04 (@EmbeddedId), Task05 (@NaturalId).
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // ===== a) Сохраняем 2 товара с ценами =====
            em.getTransaction().begin();
            Product07 p1 = new Product07("SKU-1", "Ноутбук", new Money07(50000, "RUB"));
            Product07 p2 = new Product07("SKU-2", "Телефон", new Money07(30000, "RUB"));
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
            em.clear();

            System.out.println("=== Товары сохранены ===");

            // ===== b) HashSet<Product07> — equals по sku =====
            Set<Product07> productSet = new HashSet<>();
            productSet.add(p1);
            productSet.add(p2);
            System.out.println("Set size (после добавления): " + productSet.size()); // 2

            // Перечитываем товары из БД
            Product07 found1 = em.find(Product07.class, p1.getId());
            Product07 found2 = em.find(Product07.class, p2.getId());

            System.out.println("Set contains (перечитанный p1): " + productSet.contains(found1)); // true
            System.out.println("Set contains (перечитанный p2): " + productSet.contains(found2)); // true

            // ===== c) Поиск по @NaturalId =====
            Session session = em.unwrap(Session.class);
            Product07 bySku = session.bySimpleNaturalId(Product07.class).load("SKU-1");
            System.out.println("Поиск по SKU-1: " + bySku);

            // ===== d) Сохраняем OrderLine с составным ключом =====
            em.getTransaction().begin();
            OrderLineId07 id1 = new OrderLineId07(1L, p1.getId());
            OrderLineId07 id2 = new OrderLineId07(1L, p2.getId());

            OrderLine07 ol1 = new OrderLine07(id1, 2, new Money07(100000, "RUB"));
            OrderLine07 ol2 = new OrderLine07(id2, 1, new Money07(30000, "RUB"));

            em.persist(ol1);
            em.persist(ol2);
            em.getTransaction().commit();
            em.clear();

            // Находим строку заказа по составному ключу
            OrderLineId07 searchId = new OrderLineId07(1L, p1.getId());
            OrderLine07 foundOl = em.find(OrderLine07.class, searchId);
            System.out.println("Найдено OrderLine по составному ключу: " + foundOl);

        } finally {
            em.close();
            emf.close();
        }
    }
}
