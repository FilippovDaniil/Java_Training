package m87_hibernate_deep_dive_querying.practice.task04;

/**
 * Задача 04 — Модуль 87: native SQL — маппинг в сущность и скаляр
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Засейте 4 товара.
 *   2) Native-запрос с маппингом в сущность:
 *        em.createNativeQuery("SELECT * FROM products WHERE price > :min", Product04.class)
 *          .setParameter("min", 100).getResultList();
 *      → получите управляемые Product04, выведите их.
 *   3) Native-запрос со скалярным результатом:
 *        Number total = (Number) em.createNativeQuery("SELECT count(*) FROM products")
 *          .getSingleResult();
 *      → выведите общее число.
 *   4) В комментарии отметьте минус native SQL: имена ТАБЛИЦ/КОЛОНОК (products, price),
 *      а не сущностей; теряется переносимость между СУБД.
 *
 * ЦЕЛЬ: понять, когда оправдан native SQL и как мапить его результат.
 *
 * ПОДСКАЗКА: второй аргумент createNativeQuery(sql, Product04.class) включает маппинг строк
 *            в управляемые сущности (нужны все колонки таблицы → SELECT *).
 */

import jakarta.persistence.*;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Product04("Хлеб", 40));
            em.persist(new Product04("Сыр", 300));
            em.persist(new Product04("Мышь", 800));
            em.persist(new Product04("Кабель", 120));
            em.getTransaction().commit();
            em.clear();

            // 2) маппинг в сущность
            // TODO: @SuppressWarnings("unchecked")
            // TODO: List<Product04> dear = em.createNativeQuery(
            // TODO:     "SELECT * FROM products WHERE price > :min", Product04.class)
            // TODO:     .setParameter("min", 100).getResultList();
            // TODO: dear.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

            // 3) скаляр
            // TODO: Number total = (Number) em.createNativeQuery("SELECT count(*) FROM products")
            // TODO:     .getSingleResult();
            // TODO: System.out.println("всего товаров: " + total.intValue());
        } finally {
            em.close();
            emf.close();
        }
    }
}
