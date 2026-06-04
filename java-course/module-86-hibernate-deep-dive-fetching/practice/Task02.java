/**
 * Задача 02 — Модуль 86: воспроизвести N+1 в HQL
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true — чтобы СЧИТАТЬ запросы.
 *
 * ЗАДАНИЕ:
 *   1) Засейте 3 категории по 2 товара.
 *   2) Выполните HQL "select c from Category02 c" → getResultList() (1 запрос).
 *   3) В цикле обратитесь к c.getProducts().size() для каждой — каждая итерация
 *      порождает отдельный SELECT (lazy, FetchMode.SELECT) → итого 1 + 3 = 4 запроса.
 *   4) Посчитайте SELECT'ы в логе и выведите вывод о проблеме N+1.
 *
 * ЦЕЛЬ: диагностировать N+1 на уровне HQL (как в модуле 82, но через EntityManager).
 *
 * ПОДСКАЗКА: метод должен идти в открытой транзакции, иначе lazy-доступ упадёт.
 */
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (String n : new String[]{"Электроника", "Книги", "Одежда"}) {
                Category02 c = new Category02(n);
                c.addProduct(new Product02(n + "-1"));
                c.addProduct(new Product02(n + "-2"));
                em.persist(c);
            }
            em.getTransaction().commit();
            em.clear();

            // TODO: em.getTransaction().begin();
            // TODO: List<Category02> cats = em.createQuery("select c from Category02 c", Category02.class)
            // TODO:                            .getResultList();          // 1 запрос
            // TODO: for (Category02 c : cats)
            // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size()); // +1 на каждую
            // TODO: em.getTransaction().commit();
            // TODO: System.out.println("В логе show_sql: 1 + 3 = 4 SELECT — это N+1");
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "categories")
class Category02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product02> products = new ArrayList<>();
    protected Category02() {}
    public Category02(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product02> getProducts() { return products; }
    public void addProduct(Product02 p) { products.add(p); p.setCategory(this); }
}

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category02 category;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
    public void setCategory(Category02 c) { this.category = c; }
}
