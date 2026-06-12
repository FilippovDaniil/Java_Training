package m91_hibernate_deep_dive_performance.practice.task05;

/**
 * Задача 05 — Модуль 91: StatelessSession для массовой обработки
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   StatelessSession — без persistence context, кэша 1-го уровня, dirty checking и каскадов.
 *   Идеален для ETL/импорта: стабильная память, не нужен clear().
 *   1) Получите SessionFactory: emf.unwrap(SessionFactory.class).
 *   2) Откройте StatelessSession (try-with-resources), в транзакции вставьте 10 000 товаров
 *      через ss.insert(new Product05(...)) — без накопления в контексте.
 *   3) Проверьте count через обычный EntityManager: == 10000.
 *   4) В комментарии отметьте отличия от обычного Session: нет dirty checking
 *      (нужен явный ss.update), нет каскадов, нет кэша 1-го уровня.
 *
 * ЦЕЛЬ: освоить StatelessSession как инструмент массовой обработки.
 *
 * ПОДСКАЗКА: StatelessSession реализует AutoCloseable — используйте try (StatelessSession ss = ...).
 */

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        try {
            SessionFactory sf = emf.unwrap(SessionFactory.class);
            // TODO: try (StatelessSession ss = sf.openStatelessSession()) {
            // TODO:     Transaction tx = ss.getTransaction();
            // TODO:     tx.begin();
            // TODO:     for (int i = 1; i <= 10_000; i++) ss.insert(new Product05("Товар-" + i));
            // TODO:     tx.commit();
            // TODO: }

            EntityManager em = emf.createEntityManager();
            // TODO: long n = em.createQuery("select count(p) from Product05 p", Long.class).getSingleResult();
            // TODO: System.out.println("вставлено StatelessSession: " + n); // 10000
            em.close();
        } finally {
            emf.close();
        }
    }
}
