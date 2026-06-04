/**
 * Задача 03 — Модуль 92: измерить N+1 числом запросов (Statistics)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.generate_statistics=true.
 *
 * ЗАДАНИЕ:
 *   Докажите N+1 числом, а не «на глаз».
 *   1) Засейте 4 категории по 2 товара (связь LAZY).
 *   2) st.clear(); загрузите все категории "select c from Category03 c" и в цикле
 *      обратитесь к c.getProducts().size().
 *   3) Выведите st.getPrepareStatementCount() — будет 1 + 4 = 5 (это N+1!).
 *   4) Проверьте утверждение: статистика должна равняться 1 + числу категорий.
 *      Напечатайте "N+1 ПОДТВЕРЖДЁН" если statements == 1 + categoryCount.
 *
 * ЦЕЛЬ: перевести «подозрение на N+1» в измеримый факт.
 *
 * ПОДСКАЗКА: операции — в транзакции (lazy-доступ требует сессии). clear() статистики
 *            ставьте после сидинга, перед измерением.
 */
import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.ArrayList;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 1; i <= 4; i++) {
                Category03 c = new Category03("Кат-" + i);
                c.addProduct(new Product03("Т-" + i + "-1"));
                c.addProduct(new Product03("Т-" + i + "-2"));
                em.persist(c);
            }
            em.getTransaction().commit();
            em.clear();

            // TODO: Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
            // TODO: st.clear();
            // TODO: em.getTransaction().begin();
            // TODO: List<Category03> cats = em.createQuery("select c from Category03 c", Category03.class).getResultList();
            // TODO: cats.forEach(c -> c.getProducts().size());   // +1 запрос на каждую
            // TODO: em.getTransaction().commit();
            // TODO: long statements = st.getPrepareStatementCount();
            // TODO: System.out.println("SQL-операторов: " + statements);   // 5
            // TODO: System.out.println(statements == 1 + cats.size() ? "N+1 ПОДТВЕРЖДЁН" : "ОК");
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "categories")
class Category03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product03> products = new ArrayList<>();
    protected Category03() {}
    public Category03(String name) { this.name = name; }
    public List<Product03> getProducts() { return products; }
    public void addProduct(Product03 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category03 category;
    protected Product03() {}
    public Product03(String name) { this.name = name; }
    public void setCategory(Category03 c) { this.category = c; }
}
