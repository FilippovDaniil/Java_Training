package m91_hibernate_deep_dive_performance.practice.task06;

/**
 * Задача 06 — Модуль 91: кэш 2-го уровня (@Cache) для справочных данных
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2, провайдер кэша (напр. hibernate-jcache
 *   + caffeine/ehcache) + META-INF/persistence.xml ("shop-pu").
 *   В persistence.xml: hibernate.cache.use_second_level_cache=true,
 *                      hibernate.cache.region.factory_class=jcache,
 *                      hibernate.generate_statistics=true, hibernate.show_sql=true.
 *
 * ЗАДАНИЕ:
 *   Category06 — редко меняющийся справочник, идеальный кандидат на L2-кэш.
 *   1) Пометьте сущность: @Cacheable @Cache(usage = CacheConcurrencyStrategy.READ_WRITE).
 *   2) Сохраните категорию.
 *   3) В ПЕРВОМ EntityManager: em1.find(Category06, id) → SELECT (кладёт в L2), em1.close().
 *   4) Во ВТОРОМ EntityManager: em2.find(Category06, id) → ПОПАДАНИЕ в L2, без SELECT.
 *   5) Через Statistics убедитесь в попадании:
 *        Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
 *        st.getSecondLevelCacheHitCount() > 0.
 *
 * ЦЕЛЬ: понять L2-кэш и его применимость к справочникам (высокое чтение, редкая запись).
 *
 * ПОДСКАЗКА: L2 живёт на уровне SessionFactory (между сессиями), в отличие от кэша 1-го
 *            уровня (один EntityManager). Для часто меняющихся данных L2 вреден.
 */

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        Long id;
        try {
            EntityManager setup = emf.createEntityManager();
            setup.getTransaction().begin();
            Category06 c = new Category06("Электроника");
            setup.persist(c);
            setup.getTransaction().commit();
            id = c.getId();
            setup.close();

            // TODO: EntityManager em1 = emf.createEntityManager();
            // TODO: em1.find(Category06.class, id);   // SELECT → положит в L2
            // TODO: em1.close();

            // TODO: EntityManager em2 = emf.createEntityManager();
            // TODO: em2.find(Category06.class, id);   // попадание в L2, без SELECT
            // TODO: em2.close();

            // TODO: Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
            // TODO: System.out.println("L2 hits = " + st.getSecondLevelCacheHitCount()); // > 0
        } finally {
            emf.close();
        }
    }
}
