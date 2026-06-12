package m85_hibernate_deep_dive_lifecycle.practice.task02;

/**
 * Задача 02 — Модуль 85: dirty checking (UPDATE без merge/save)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) В одной транзакции сохраните Product02("Хлеб", 40).
 *   2) В НОВОЙ транзакции: найдите товар (em.find) — он managed, Hibernate сделал снимок.
 *      Измените цену p.setPrice(35). НЕ вызывайте merge/persist.
 *   3) Закоммитьте — на commit Hibernate сравнит со снимком и сам выполнит UPDATE.
 *   4) В ещё одной транзакции перечитайте и убедитесь, что цена = 35.
 *
 * ЦЕЛЬ: убедиться, что для managed-сущности изменение поля = автоматический UPDATE.
 *
 * ПОДСКАЗКА: включите show_sql в persistence.xml (hibernate.show_sql=true) — увидите
 *            ровно один UPDATE без вашего явного вызова сохранения.
 */

import jakarta.persistence.*;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product02 p = new Product02("Хлеб", 40);
            em.persist(p);
            em.getTransaction().commit();
            id = p.getId();

            // TODO: em.getTransaction().begin();
            // TODO: Product02 managed = em.find(Product02.class, id);
            // TODO: managed.setPrice(35);          // НЕ merge/persist
            // TODO: em.getTransaction().commit();  // dirty checking → UPDATE

            // TODO: проверка:
            // TODO: Product02 reloaded = em.find(Product02.class, id);
            // TODO: System.out.println("цена после dirty checking = " + reloaded.getPrice()); // 35
        } finally {
            em.close();
            emf.close();
        }
    }
}
