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
