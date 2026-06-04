/**
 * Задача 01 — Модуль 85: transient → managed (persist), идентичность контекста
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml
 *   с persistence-unit "shop-pu" (см. theory.md). Работаем через EntityManager напрямую.
 *
 * ЗАДАНИЕ:
 *   1) Создайте new Product01("Молоко") — это transient (id == null, в контексте нет).
 *      Проверьте em.contains(p) == false до persist.
 *   2) em.persist(p) — объект становится managed; после flush id присвоен (IDENTITY).
 *      Проверьте em.contains(p) == true и p.getId() != null.
 *   3) Дважды вызовите em.find(Product01.class, p.getId()) и убедитесь, что возвращается
 *      ТОТ ЖЕ объект (a == b) — гарантия идентичности кэша 1-го уровня.
 *
 * ЦЕЛЬ: прочувствовать переход transient → managed и identity-гарантию контекста.
 *
 * ПОДСКАЗКА: em.contains(entity) проверяет, управляется ли объект текущим контекстом.
 */
import jakarta.persistence.*;

public class Task01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product01 p = new Product01("Молоко");
            // TODO: System.out.println("до persist contains=" + em.contains(p) + " id=" + p.getId());
            // TODO: em.persist(p);
            // TODO: System.out.println("после persist contains=" + em.contains(p) + " id=" + p.getId());
            // TODO: Product01 a = em.find(Product01.class, p.getId());
            // TODO: Product01 b = em.find(Product01.class, p.getId());
            // TODO: System.out.println("a == b ? " + (a == b));   // true
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Product01() {}
    public Product01(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}
