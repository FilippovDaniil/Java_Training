/**
 * Задача 02 — Модуль 91: flush()+clear() в цикле массовой вставки
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.jdbc.batch_size=50.
 *
 * ЗАДАНИЕ:
 *   Persistence context накапливает ВСЕ управляемые сущности → рост памяти и замедление
 *   dirty checking. Очищайте его периодически.
 *   1) В транзакции вставьте 10 000 товаров в цикле.
 *   2) Каждые 50 итераций (= batch_size) вызывайте em.flush(); em.clear();
 *      — пачка уходит в БД, контекст освобождается.
 *   3) После цикла проверьте count: "select count(p) from Product02 p" == 10000.
 *   4) В комментарии: без flush/clear на больших объёмах возможен OutOfMemoryError.
 *
 * ЦЕЛЬ: освоить паттерн пакетной вставки без раздувания persistence context.
 *
 * ПОДСКАЗКА: шаг очистки согласуйте с batch_size; clear() делает прежние объекты detached.
 */
import jakarta.persistence.*;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // TODO: for (int i = 1; i <= 10_000; i++) {
            // TODO:     em.persist(new Product02("Товар-" + i));
            // TODO:     if (i % 50 == 0) { em.flush(); em.clear(); }
            // TODO: }
            em.getTransaction().commit();
            // TODO: long n = em.createQuery("select count(p) from Product02 p", Long.class).getSingleResult();
            // TODO: System.out.println("вставлено: " + n); // 10000
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p2_seq")
    @SequenceGenerator(name = "p2_seq", sequenceName = "p2_seq", allocationSize = 50)
    private Long id;
    private String name;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
}
