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

// TODO: @Embeddable
class PriceListLineId04 implements Serializable {
    private Long priceListId;
    private Long productId;
    protected PriceListLineId04() {}
    public PriceListLineId04(Long priceListId, Long productId) {
        this.priceListId = priceListId; this.productId = productId;
    }
    // TODO: equals/hashCode ПО ОБОИМ полям:
    // TODO: @Override public boolean equals(Object o) {
    // TODO:     return o instanceof PriceListLineId04 k
    // TODO:         && Objects.equals(priceListId, k.priceListId)
    // TODO:         && Objects.equals(productId, k.productId);
    // TODO: }
    // TODO: @Override public int hashCode() { return Objects.hash(priceListId, productId); }
}

@Entity @Table(name = "price_list_lines")
class PriceListLine04 {
    // TODO: @EmbeddedId
    private PriceListLineId04 id;
    private int price;
    protected PriceListLine04() {}
    public PriceListLine04(PriceListLineId04 id, int price) { this.id = id; this.price = price; }
    public int getPrice() { return price; }
}
