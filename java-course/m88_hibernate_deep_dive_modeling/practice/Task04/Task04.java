package m88_hibernate_deep_dive_modeling.practice.task04;

/**
 * Задача 04 — Модуль 88: составной ключ через @EmbeddedId
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Строка прайса PriceListLine04 идентифицируется парой (priceListId, productId).
 *
 *   1) Сделайте PriceListLineId04 составным ключом:
 *        - @Embeddable, implements Serializable;
 *        - поля Long priceListId, Long productId;
 *        - equals/hashCode ПО ОБОИМ полям (обязательно!).
 *   2) В PriceListLine04: @EmbeddedId private PriceListLineId04 id; + поле price.
 *   3) Сохраните две строки с разными ключами; найдите по составному ключу
 *      em.find(PriceListLine04.class, new PriceListLineId04(1L, 10L)); выведите цену.
 *
 * ЦЕЛЬ: освоить составной первичный ключ и роль equals/hashCode для него.
 *
 * ПОДСКАЗКА: без Serializable + equals/hashCode на классе ключа Hibernate не сможет
 *            идентифицировать строки → ошибки при find/persist.
 */

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new PriceListLine04(new PriceListLineId04(1L, 10L), 500));
            em.persist(new PriceListLine04(new PriceListLineId04(1L, 11L), 800));
            em.getTransaction().commit();
            em.clear();

            // TODO: PriceListLine04 line = em.find(PriceListLine04.class, new PriceListLineId04(1L, 10L));
            // TODO: System.out.println("цена строки (1,10) = " + line.getPrice()); // 500
        } finally {
            em.close();
            emf.close();
        }
    }
}
