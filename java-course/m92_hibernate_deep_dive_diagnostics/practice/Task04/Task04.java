package m92_hibernate_deep_dive_diagnostics.practice.task04;

/**
 * Задача 04 — Модуль 92: до/после — починить N+1 и доказать снижение числа запросов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.generate_statistics=true.
 *
 * ЗАДАНИЕ:
 *   Сравните две реализации одной задачи и докажите улучшение метрикой.
 *   Засейте 5 категорий по 3 товара (LAZY).
 *
 *   1) countNaive(em): "select c from Category04 c" + цикл по products.size().
 *      Измерьте statements через Statistics → ожидается 1 + 5 = 6.
 *   2) countFetch(em): "select distinct c from Category04 c join fetch c.products"
 *      + цикл по products.size(). Измерьте statements → ожидается 1.
 *   3) Выведите оба числа и вывод: JOIN FETCH убрал N+1 (6 → 1).
 *
 * ЦЕЛЬ: закрепить цикл «измерил → нашёл N+1 → починил → измерил снова».
 *
 * ПОДСКАЗКА: st.clear() ПЕРЕД каждым измерением; оба метода — в транзакции.
 */

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 1; i <= 5; i++) {
                Category04 c = new Category04("Кат-" + i);
                for (int j = 1; j <= 3; j++) c.addProduct(new Product04("Т-" + i + "-" + j));
                em.persist(c);
            }
            em.getTransaction().commit();
            em.clear();

            Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
            // TODO: long naive = countNaive(em, st);   // 6
            // TODO: long fetch = countFetch(em, st);    // 1
            // TODO: System.out.println("naive statements = " + naive + ", fetch statements = " + fetch);
            // TODO: System.out.println("JOIN FETCH убрал N+1: " + naive + " → " + fetch);
        } finally {
            em.close();
            emf.close();
        }
    }

    static long countNaive(EntityManager em, Statistics st) {
        // TODO: st.clear();
        // TODO: em.getTransaction().begin();
        // TODO: em.createQuery("select c from Category04 c", Category04.class)
        // TODO:   .getResultList().forEach(c -> c.getProducts().size());
        // TODO: em.getTransaction().commit(); em.clear();
        // TODO: return st.getPrepareStatementCount();
        return -1;
    }

    static long countFetch(EntityManager em, Statistics st) {
        // TODO: st.clear();
        // TODO: em.getTransaction().begin();
        // TODO: em.createQuery("select distinct c from Category04 c join fetch c.products", Category04.class)
        // TODO:   .getResultList().forEach(c -> c.getProducts().size());
        // TODO: em.getTransaction().commit(); em.clear();
        // TODO: return st.getPrepareStatementCount();
        return -1;
    }
}
