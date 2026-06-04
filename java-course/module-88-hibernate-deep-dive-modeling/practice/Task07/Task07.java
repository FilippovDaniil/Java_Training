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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // TODO: a) сохранить 2 Product07 (SKU-1/SKU-2) с Money07
            // TODO: b) HashSet<Product07>: size==2, contains(перечитанного по find) == true
            // TODO: c) session.bySimpleNaturalId(Product07.class).load("SKU-1")
            // TODO: d) сохранить 2 OrderLine07 с составными ключами; найти одну по new OrderLineId07(...)
        } finally {
            em.close();
            emf.close();
        }
    }
}
