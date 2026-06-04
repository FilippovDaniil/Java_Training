/**
 * Задача 05 — Модуль 87: owning side — синхронизация обеих сторон связи
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   ЧАСТЬ A — ловушка (FK не запишется):
 *     1) Создайте Category05 и Product05.
 *     2) Добавьте товар ТОЛЬКО в обратную сторону: category.getProducts().add(product),
 *        НЕ вызывая product.setCategory(category). Сохраните оба.
 *     3) Перечитайте product и выведите его category — будет null (Hibernate смотрит на
 *        владельца @ManyToOne, а вы его не выставили).
 *
 *   ЧАСТЬ B — правильно (через хелпер):
 *     4) Используйте category.addProduct(product), который синхронизирует ОБЕ стороны
 *        (products.add + product.setCategory(this)). Сохраните, перечитайте — category != null.
 *
 * ЦЕЛЬ: понять, что FK пишет ВЛАДЕЛЕЦ связи, и всегда синхронизировать обе стороны.
 *
 * ПОДСКАЗКА: владелец — сторона с @JoinColumn (@ManyToOne); mappedBy-сторона FK не пишет.
 */
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // ЧАСТЬ A — ловушка
            em.getTransaction().begin();
            Category05 cat = new Category05("Электроника");
            Product05 wrong = new Product05("Ноутбук");
            // TODO: cat.getProducts().add(wrong);   // ТОЛЬКО обратная сторона
            em.persist(cat);
            em.persist(wrong);
            em.getTransaction().commit();
            Long wrongId = wrong.getId();
            em.clear();
            // TODO: System.out.println("FK (ловушка) category = " + em.find(Product05.class, wrongId).getCategory()); // null

            // ЧАСТЬ B — правильно
            em.getTransaction().begin();
            Category05 cat2 = new Category05("Книги");
            Product05 ok = new Product05("Java");
            // TODO: cat2.addProduct(ok);   // синхронизирует обе стороны
            em.persist(cat2);
            em.persist(ok);
            em.getTransaction().commit();
            Long okId = ok.getId();
            em.clear();
            // TODO: System.out.println("FK (правильно) category = " + em.find(Product05.class, okId).getCategory()); // не null
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "categories")
class Category05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")   // обратная сторона (не владелец)
    private List<Product05> products = new ArrayList<>();
    protected Category05() {}
    public Category05(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product05> getProducts() { return products; }
    /** Хелпер: синхронизирует ОБЕ стороны связи. */
    public void addProduct(Product05 p) {
        products.add(p);
        p.setCategory(this);   // ВЛАДЕЛЕЦ — пишет FK
    }
}

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne @JoinColumn(name = "category_id")   // владелец связи (FK)
    private Category05 category;
    protected Product05() {}
    public Product05(String name) { this.name = name; }
    public Long getId() { return id; }
    public Category05 getCategory() { return category; }
    public void setCategory(Category05 c) { this.category = c; }
}
