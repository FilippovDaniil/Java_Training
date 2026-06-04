/**
 * Задача 05 — Модуль 85: flush — когда реально выполняется SQL
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true — чтобы видеть моменты выполнения SQL.
 *
 * ЗАДАНИЕ:
 *   1) В транзакции: em.persist(new Product05("A", 1)). INSERT ОТЛОЖЕН.
 *      Выведите строку "после persist (SQL ещё не на диске)".
 *   2) Вызовите em.flush() — теперь INSERT выполняется. Выведите "после flush".
 *      Сопоставьте с логом show_sql: INSERT появляется именно на flush.
 *   3) Продемонстрируйте авто-flush перед запросом: persist ещё один товар БЕЗ flush,
 *      затем выполните JPQL-count (em.createQuery("select count(p) from Product05 p", Long.class))
 *      — Hibernate сделает авто-flush перед запросом, count учтёт оба товара.
 *
 * ЦЕЛЬ: разделить понятия «изменение в контексте» и «SQL в БД»; понять триггеры flush.
 *
 * ПОДСКАЗКА: FlushModeType.AUTO (по умолчанию) делает flush перед commit И перед запросами.
 */

import jakarta.persistence.*;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // TODO: em.persist(new Product05("A", 1));
            // TODO: System.out.println("после persist — INSERT отложен");
            // TODO: em.flush();
            // TODO: System.out.println("после flush — INSERT выполнен");

            // TODO: em.persist(new Product05("B", 2));   // снова отложено
            // TODO: Long count = em.createQuery("select count(p) from Product05 p", Long.class)
            // TODO:                .getSingleResult();   // авто-flush ПЕРЕД запросом
            // TODO: System.out.println("count = " + count); // 2 (оба учтены)
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}
