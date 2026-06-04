/**
 * Задача 02 — Модуль 88: value object через @Embeddable / @Embedded (Money)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Сделайте класс Money02 встраиваемым: пометьте @Embeddable; поля amount (long, копейки)
 *      и currency (String). Реализуйте equals/hashCode ПО ЗНАЧЕНИЮ (оба поля).
 *   2) В Product02 встройте цену: @Embedded private Money02 price; (колонки amount/currency
 *      окажутся в таблице products).
 *   3) Сохраните товар с ценой Money02(10000, "RUB"). Перечитайте — проверьте, что
 *      price восстановилась и Money02(10000,"RUB").equals(перечитанной) == true (равенство по значению).
 *
 * ЦЕЛЬ: понять value object — нет своего id, хранится в колонках сущности, равен по значению.
 *
 * ПОДСКАЗКА: у @Embeddable нужен protected конструктор без аргументов (для Hibernate).
 */
import jakarta.persistence.*;

import java.util.Objects;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product02 p = new Product02("Ноутбук", new Money02(10000, "RUB"));
            em.persist(p);
            em.getTransaction().commit();
            id = p.getId();
            em.clear();

            // TODO: Product02 reloaded = em.find(Product02.class, id);
            // TODO: System.out.println("цена: " + reloaded.getPrice().getAmount() + " " + reloaded.getPrice().getCurrency());
            // TODO: System.out.println("равны по значению? " +
            // TODO:     new Money02(10000, "RUB").equals(reloaded.getPrice())); // true
        } finally {
            em.close();
            emf.close();
        }
    }
}

// TODO: @Embeddable
class Money02 {
    private long amount;
    private String currency;
    protected Money02() {}
    public Money02(long amount, String currency) { this.amount = amount; this.currency = currency; }
    public long getAmount() { return amount; }
    public String getCurrency() { return currency; }
    // TODO: equals/hashCode ПО ЗНАЧЕНИЮ:
    // TODO: @Override public boolean equals(Object o) {
    // TODO:     return o instanceof Money02 m && amount == m.amount && Objects.equals(currency, m.currency);
    // TODO: }
    // TODO: @Override public int hashCode() { return Objects.hash(amount, currency); }
}

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // TODO: @Embedded
    private Money02 price;
    protected Product02() {}
    public Product02(String name, Money02 price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public Money02 getPrice() { return price; }
}
