package m90_hibernate_deep_dive_locking.practice.task05;

/**
 * Задача 05 — Модуль 90: soft delete (@SQLDelete + @SQLRestriction)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true — увидеть, что DELETE подменяется на UPDATE.
 *
 * ЗАДАНИЕ:
 *   1) Сделайте Product05 «мягко удаляемым»:
 *        @SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ?")
 *        @SQLRestriction("deleted = false")
 *      Поле private boolean deleted = false;
 *   2) Сохраните 3 товара.
 *   3) em.remove(один товар) — в логе будет UPDATE ... deleted=true (строка остаётся в БД).
 *   4) Запросите все товары через "select p from Product05 p" — вернётся 2 (удалённый
 *      скрыт фильтром @SQLRestriction). Проверьте, что физически строка ещё есть
 *      нативным "SELECT count(*) FROM products" → 3.
 *
 * ЦЕЛЬ: освоить мягкое удаление: данные сохраняются, но не видны обычным запросам.
 *
 * ПОДСКАЗКА: @SQLRestriction (Hibernate 6.3+) заменил @Where; добавляет условие во ВСЕ SELECT.
 */

import jakarta.persistence.*;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long victimId;
        try {
            em.getTransaction().begin();
            Product05 a = new Product05("Хлеб");
            em.persist(a);
            em.persist(new Product05("Молоко"));
            em.persist(new Product05("Сыр"));
            em.getTransaction().commit();
            victimId = a.getId();

            // TODO: em.getTransaction().begin();
            // TODO: em.remove(em.find(Product05.class, victimId));   // UPDATE deleted=true, не DELETE
            // TODO: em.getTransaction().commit();
            // TODO: em.clear();

            // TODO: long visible = em.createQuery("select count(p) from Product05 p", Long.class).getSingleResult();
            // TODO: System.out.println("видно через JPA: " + visible); // 2 (фильтр deleted=false)
            // TODO: Number physical = (Number) em.createNativeQuery("SELECT count(*) FROM products").getSingleResult();
            // TODO: System.out.println("физически в таблице: " + physical.intValue()); // 3
        } finally {
            em.close();
            emf.close();
        }
    }
}
