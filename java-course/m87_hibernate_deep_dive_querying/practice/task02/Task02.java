package m87_hibernate_deep_dive_querying.practice.task02;

/**
 * Задача 02 — Модуль 87: bulk update/delete и ловушка кэша контекста
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Засейте 3 товара категории "Еда" с ценами 40/80/150.
 *   2) В транзакции загрузите один товар в контекст (em.find) и запомните его цену.
 *   3) Выполните bulk-update: "update Product02 p set p.price = p.price + 10 where p.category = 'Еда'"
 *      .executeUpdate() → вернёт число обновлённых строк (3).
 *   4) ЛОВУШКА: выведите цену ранее загруженного объекта — она НЕ изменилась (bulk прошёл
 *      мимо persistence context). Вызовите em.refresh(p) (или em.clear() + повторный find)
 *      — теперь цена обновлена (+10).
 *
 * ЦЕЛЬ: понять, что bulk-операции не синхронизируют объекты в контексте.
 *
 * ПОДСКАЗКА: после bulk DML делайте em.clear()/refresh, иначе работаете с устаревшими данными.
 */

import jakarta.persistence.*;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product02 a = new Product02("Хлеб", "Еда", 40);
            em.persist(a);
            em.persist(new Product02("Молоко", "Еда", 80));
            em.persist(new Product02("Чай", "Еда", 150));
            em.getTransaction().commit();
            id = a.getId();

            // TODO: em.getTransaction().begin();
            // TODO: Product02 p = em.find(Product02.class, id);
            // TODO: System.out.println("до bulk (в контексте) = " + p.getPrice()); // 40
            // TODO: int updated = em.createQuery(
            // TODO:     "update Product02 p set p.price = p.price + 10 where p.category = 'Еда'")
            // TODO:     .executeUpdate();
            // TODO: System.out.println("обновлено строк: " + updated); // 3
            // TODO: System.out.println("объект в контексте всё ещё = " + p.getPrice()); // 40 (ловушка!)
            // TODO: em.refresh(p);
            // TODO: System.out.println("после refresh = " + p.getPrice()); // 50
            // TODO: em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}
