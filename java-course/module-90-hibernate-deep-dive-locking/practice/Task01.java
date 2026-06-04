/**
 * Задача 01 — Модуль 90: оптимистичная блокировка @Version и OptimisticLockException
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Смоделируйте потерянное обновление двумя ОТДЕЛЬНЫМИ EntityManager (две «сессии»):
 *   1) Product01 уже содержит @Version.
 *   2) em1 и em2 НЕЗАВИСИМО загрузили товар (обе видят version=0).
 *   3) em1: stock -= 1; commit → version станет 1.
 *   4) em2: stock -= 2; commit → UPDATE ... WHERE version=0 обновит 0 строк →
 *      OptimisticLockException (или RollbackException с ней внутри). Поймайте и напечатайте.
 *
 * ЦЕЛЬ: увидеть оптимистичную блокировку «вживую» через две конкурентные сессии.
 *
 * ПОДСКАЗКА: два отдельных em = две независимые копии в своих persistence context.
 *            Вторая фиксация работает с устаревшей версией → конфликт.
 */
import jakarta.persistence.*;

public class Task01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager setup = emf.createEntityManager();
        Long id;
        setup.getTransaction().begin();
        Product01 p = new Product01("Видеокарта", 10);
        setup.persist(p);
        setup.getTransaction().commit();
        id = p.getId();
        setup.close();

        EntityManager em1 = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();
        try {
            // обе сессии читают version=0
            // TODO: em1.getTransaction().begin(); Product01 a = em1.find(Product01.class, id);
            // TODO: em2.getTransaction().begin(); Product01 b = em2.find(Product01.class, id);

            // em1 фиксирует первым
            // TODO: a.setStock(a.getStock() - 1); em1.getTransaction().commit(); // version → 1

            // em2 фиксирует устаревшую версию → конфликт
            // TODO: try {
            // TODO:     b.setStock(b.getStock() - 2); em2.getTransaction().commit();
            // TODO: } catch (Exception e) {
            // TODO:     System.out.println("Конфликт: " + e.getClass().getSimpleName());
            // TODO: }
        } finally {
            em1.close();
            em2.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    @Version
    private long version;
    protected Product01() {}
    public Product01(String name, int stock) { this.name = name; this.stock = stock; }
    public Long getId() { return id; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
