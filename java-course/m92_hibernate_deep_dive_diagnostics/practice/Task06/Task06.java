package m92_hibernate_deep_dive_diagnostics.practice.task06;

/**
 * Задача 06 — Модуль 92: «предохранитель» — тест на число запросов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.generate_statistics=true.
 *
 * ЗАДАНИЕ:
 *   Напишите функцию-проверку, которая ФИКСИРУЕТ ожидаемое число SQL-операторов для
 *   операции — чтобы будущая регрессия (случайный N+1) была поймана.
 *   1) Засейте 3 категории по 2 товара.
 *   2) assertQueryCount(st, expected, операция): clear() → выполнить операцию →
 *      сравнить getPrepareStatementCount() с expected; печатать "PASS"/"FAIL".
 *   3) Проверьте загрузку с JOIN FETCH: ожидается ровно 1 запрос → PASS.
 *   4) Для контраста проверьте «наивную» загрузку с ожиданием 1 → получите FAIL
 *      (реально 1 + 3). Это и есть сработавший предохранитель.
 *
 * ЦЕЛЬ: оформить защиту от N+1 как воспроизводимую проверку (зерно для @DataJpaTest).
 *
 * ПОДСКАЗКА: в реальном проекте такую проверку оформляют как JUnit-тест с
 *            assertThat(stats.getPrepareStatementCount()).isEqualTo(1).
 */

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 1; i <= 3; i++) {
                Category06 c = new Category06("Кат-" + i);
                c.addProduct(new Product06("Т-" + i + "-1"));
                c.addProduct(new Product06("Т-" + i + "-2"));
                em.persist(c);
            }
            em.getTransaction().commit();
            em.clear();

            Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
            // TODO: assertQueryCount(em, st, 1, () ->
            // TODO:     em.createQuery("select distinct c from Category06 c join fetch c.products", Category06.class)
            // TODO:       .getResultList().forEach(c -> c.getProducts().size()));   // PASS

            // TODO: assertQueryCount(em, st, 1, () ->
            // TODO:     em.createQuery("select c from Category06 c", Category06.class)
            // TODO:       .getResultList().forEach(c -> c.getProducts().size()));   // FAIL (реально 1+3)
        } finally {
            em.close();
            emf.close();
        }
    }

    static void assertQueryCount(EntityManager em, Statistics st, long expected, Runnable op) {
        // TODO: st.clear();
        // TODO: em.getTransaction().begin();
        // TODO: op.run();
        // TODO: em.getTransaction().commit(); em.clear();
        // TODO: long actual = st.getPrepareStatementCount();
        // TODO: System.out.println((actual == expected ? "PASS" : "FAIL") +
        // TODO:                    " (ожидалось " + expected + ", было " + actual + ")");
    }
}
