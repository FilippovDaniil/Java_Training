package m91_hibernate_deep_dive_performance.practice.task04;

/**
 * Задача 04 — Модуль 91: read-only запрос (отключение dirty checking)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true.
 *
 * ЗАДАНИЕ:
 *   1) Засейте 3 товара.
 *   2) Выполните read-only выборку:
 *        List<Product04> list = em.createQuery("select p from Product04 p", Product04.class)
 *            .setHint("org.hibernate.readOnly", true)
 *            .getResultList();
 *      Hibernate НЕ делает снимки для dirty checking — меньше памяти и CPU.
 *   3) Докажите: измените p.setPrice(...) у read-only объекта и закоммитьте —
 *      UPDATE в логе НЕ появится (изменение не отслеживается). Перечитайте — цена прежняя.
 *
 * ЦЕЛЬ: понять смысл read-only для чисто читающих сценариев (отчёты, экспорт).
 *
 * ПОДСКАЗКА: read-only ≠ запрет на setX в Java; это значит «Hibernate не будет
 *            синхронизировать изменения с БД».
 */

import jakarta.persistence.*;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product04 first = new Product04("Хлеб", 40);
            em.persist(first);
            em.persist(new Product04("Молоко", 80));
            em.persist(new Product04("Сыр", 300));
            em.getTransaction().commit();
            id = first.getId();
            em.clear();

            // TODO: em.getTransaction().begin();
            // TODO: List<Product04> list = em.createQuery("select p from Product04 p", Product04.class)
            // TODO:     .setHint("org.hibernate.readOnly", true).getResultList();
            // TODO: list.get(0).setPrice(99999);   // изменение НЕ будет сохранено
            // TODO: em.getTransaction().commit();
            // TODO: em.clear();
            // TODO: System.out.println("цена после read-only правки: " + em.find(Product04.class, id).getPrice()); // 40
        } finally {
            em.close();
            emf.close();
        }
    }
}
