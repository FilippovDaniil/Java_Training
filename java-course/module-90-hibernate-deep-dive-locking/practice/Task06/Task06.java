/**
 * Задача 06 — Модуль 90: аудит истории через Hibernate Envers
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, org.hibernate.orm:hibernate-envers, com.h2database:h2
 *   + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Пометьте Product06 аннотацией @Audited (Envers создаст products_AUD + REVINFO).
 *   2) Сценарий, порождающий историю:
 *        - сохранить Product06("Молоко", 50);            // ревизия 1
 *        - в новой транзакции изменить цену на 60;        // ревизия 2
 *        - ещё раз изменить на 55;                        // ревизия 3
 *   3) Через AuditReader прочитайте историю:
 *        AuditReader reader = AuditReaderFactory.get(em);
 *        List<Number> revs = reader.getRevisions(Product06.class, id);  // [1,2,3]
 *        для каждой ревизии: reader.find(Product06.class, id, rev).getPrice() → 50, 60, 55.
 *   4) Выведите цену на каждой ревизии («машина времени»).
 *
 * ЦЕЛЬ: освоить аудит ПОЛНОЙ истории изменений (в отличие от @Version — он лишь счётчик).
 *
 * ПОДСКАЗКА: AuditReaderFactory.get(em) — точка входа в Envers; getRevisions даёт номера ревизий.
 */

import jakarta.persistence.*;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product06 p = new Product06("Молоко", 50);
            em.persist(p);
            em.getTransaction().commit();          // ревизия 1
            id = p.getId();

            // TODO: em.getTransaction().begin(); em.find(Product06.class, id).setPrice(60); em.getTransaction().commit(); // рев. 2
            // TODO: em.getTransaction().begin(); em.find(Product06.class, id).setPrice(55); em.getTransaction().commit(); // рев. 3

            // TODO: AuditReader reader = AuditReaderFactory.get(em);
            // TODO: List<Number> revs = reader.getRevisions(Product06.class, id);
            // TODO: for (Number rev : revs)
            // TODO:     System.out.println("рев. " + rev + ": price = " +
            // TODO:         reader.find(Product06.class, id, rev).getPrice());
            // TODO: // ожидается: 50, 60, 55
        } finally {
            em.close();
            emf.close();
        }
    }
}
