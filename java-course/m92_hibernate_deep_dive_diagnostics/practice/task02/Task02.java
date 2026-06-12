package m92_hibernate_deep_dive_diagnostics.practice.task02;

/**
 * Задача 02 — Модуль 92: Statistics API — измеримая диагностика
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.generate_statistics=true.
 *
 * ЗАДАНИЕ:
 *   1) Получите Statistics: emf.unwrap(SessionFactory.class).getStatistics().
 *   2) Засейте 5 товаров.
 *   3) st.clear(); затем выполните "select p from Product02 p" и переберите результат.
 *   4) Выведите счётчики:
 *        st.getQueryExecutionCount()   — число выполненных HQL-запросов;
 *        st.getEntityLoadCount()       — сколько сущностей загружено;
 *        st.getPrepareStatementCount() — число подготовленных SQL-операторов.
 *
 * ЦЕЛЬ: научиться измерять поведение Hibernate числами, а не «на глаз».
 *
 * ПОДСКАЗКА: clear() обнуляет счётчики — вызывайте его ПЕРЕД измеряемым участком.
 */

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 1; i <= 5; i++) em.persist(new Product02("Товар-" + i));
            em.getTransaction().commit();
            em.clear();

            // TODO: Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
            // TODO: st.clear();
            // TODO: em.createQuery("select p from Product02 p", Product02.class).getResultList().size();
            // TODO: System.out.println("queries = " + st.getQueryExecutionCount());     // 1
            // TODO: System.out.println("entityLoads = " + st.getEntityLoadCount());      // 5
            // TODO: System.out.println("statements = " + st.getPrepareStatementCount()); // 1
        } finally {
            em.close();
            emf.close();
        }
    }
}
