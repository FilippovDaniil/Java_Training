/**
 * Задача 03 — Модуль 91: bulk update vs построчная обработка
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true.
 *
 * ЗАДАНИЕ:
 *   Поднять цену всем товарам категории "Еда" на 10.
 *   1) Засейте 5 товаров категории "Еда" и 3 — "Техника".
 *   2) ПОСТРОЧНО (анти-паттерн для массовых правок): загрузить findByCategory через HQL
 *      и в цикле setPrice(+10) — в логе будет SELECT + N UPDATE.
 *   3) BULK (правильно): один запрос
 *        "update Product03 p set p.price = p.price + 10 where p.category = 'Еда'"
 *        .executeUpdate() → вернёт число строк (5), один UPDATE в логе.
 *   4) ВАЖНО: после bulk вызовите em.clear() — иначе объекты в контексте устарели.
 *      Перечитайте и проверьте цены.
 *
 * ЦЕЛЬ: понять, когда выбирать bulk-операцию, и про обязательный clear() после неё.
 *
 * ПОДСКАЗКА: bulk идёт мимо persistence context — это и быстрее, и причина устаревания кэша.
 */

import jakarta.persistence.*;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 1; i <= 5; i++) em.persist(new Product03("Еда-" + i, "Еда", 100));
            for (int i = 1; i <= 3; i++) em.persist(new Product03("Тех-" + i, "Техника", 500));
            em.getTransaction().commit();
            em.clear();

            // BULK
            // TODO: em.getTransaction().begin();
            // TODO: int n = em.createQuery(
            // TODO:     "update Product03 p set p.price = p.price + 10 where p.category = 'Еда'")
            // TODO:     .executeUpdate();
            // TODO: em.getTransaction().commit();
            // TODO: em.clear();   // обязательно после bulk
            // TODO: System.out.println("обновлено строк: " + n); // 5
            // TODO: System.out.println("цена первой еды: " +
            // TODO:     em.createQuery("select p.price from Product03 p where p.category='Еда'", Integer.class)
            // TODO:       .setMaxResults(1).getSingleResult()); // 110
        } finally {
            em.close();
            emf.close();
        }
    }
}
