/**
 * Задача 05 — Модуль 88: @NaturalId и поиск по естественному ключу
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) В Product05 оставьте суррогатный @Id @GeneratedValue Long id И добавьте
 *      бизнес-ключ: @NaturalId(mutable = false) private String sku;
 *   2) equals/hashCode реализуйте по sku (естественный ключ).
 *   3) Сохраните 2 товара с разными sku.
 *   4) Получите Hibernate Session из EntityManager и найдите товар по natural id:
 *        Session session = em.unwrap(Session.class);
 *        Product05 p = session.bySimpleNaturalId(Product05.class).load("SKU-123");
 *      Выведите имя найденного.
 *
 * ЦЕЛЬ: совместить суррогатный PK (для FK/производительности) и @NaturalId (бизнес-смысл),
 *       освоить bySimpleNaturalId.
 *
 * ПОДСКАЗКА: em.unwrap(Session.class) даёт доступ к нативному Hibernate Session API.
 */
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Product05("SKU-123", "Ноутбук"));
            em.persist(new Product05("SKU-456", "Телефон"));
            em.getTransaction().commit();
            em.clear();

            // TODO: Session session = em.unwrap(Session.class);
            // TODO: Product05 p = session.bySimpleNaturalId(Product05.class).load("SKU-123");
            // TODO: System.out.println("найдено по natural id: " + p.getName()); // Ноутбук
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @NaturalId(mutable = false)
    @Column(unique = true)
    private String sku;

    private String name;
    protected Product05() {}
    public Product05(String sku, String name) { this.sku = sku; this.name = name; }
    public String getName() { return name; }
    // TODO: equals/hashCode по sku:
    // TODO: @Override public boolean equals(Object o) {
    // TODO:     return o instanceof Product05 p && sku != null && sku.equals(p.sku);
    // TODO: }
    // TODO: @Override public int hashCode() { return sku != null ? sku.hashCode() : 0; }
}
