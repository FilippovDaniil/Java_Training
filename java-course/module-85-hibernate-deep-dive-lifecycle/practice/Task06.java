/**
 * Задача 06 — Модуль 85: refresh и кэш 1-го уровня
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   ЧАСТЬ A — refresh отменяет изменения в памяти:
 *     1) Сохраните Product06("Кофе", 500).
 *     2) В транзакции: найдите managed-объект, испортите цену p.setPrice(0) (ещё не flushed).
 *        Вызовите em.refresh(p) — SELECT перезатрёт объект данными из БД (price=500).
 *        Выведите цену после refresh (500).
 *
 *   ЧАСТЬ B — кэш 1-го уровня:
 *     3) В одном контексте дважды em.find по тому же id — второй раз БЕЗ запроса
 *        (объект из кэша). Сравните a == b (true).
 *     4) em.clear() очищает контекст; следующий find снова сделает SELECT и вернёт
 *        ДРУГОЙ объект (c != a).
 *
 * ЦЕЛЬ: понять refresh (память ← БД) и роль кэша 1-го уровня внутри контекста.
 *
 * ПОДСКАЗКА: refresh — это «откатить мои изменения к версии в БД»; clear() — «забыть всё».
 */
import jakarta.persistence.*;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product06 p = new Product06("Кофе", 500);
            em.persist(p);
            em.getTransaction().commit();
            id = p.getId();

            // ЧАСТЬ A
            // TODO: em.getTransaction().begin();
            // TODO: Product06 managed = em.find(Product06.class, id);
            // TODO: managed.setPrice(0);              // ошибочное изменение в памяти
            // TODO: em.refresh(managed);              // SELECT — вернёт 500
            // TODO: System.out.println("после refresh = " + managed.getPrice()); // 500
            // TODO: em.getTransaction().commit();

            // ЧАСТЬ B
            // TODO: Product06 a = em.find(Product06.class, id);
            // TODO: Product06 b = em.find(Product06.class, id);  // из кэша 1-го уровня
            // TODO: System.out.println("a == b ? " + (a == b)); // true
            // TODO: em.clear();
            // TODO: Product06 c = em.find(Product06.class, id);  // новый SELECT
            // TODO: System.out.println("c == a ? " + (c == a)); // false
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product06() {}
    public Product06(String name, int price) { this.name = name; this.price = price; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
