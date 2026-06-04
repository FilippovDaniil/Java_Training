/**
 * Задача 04 — Модуль 89: @MappedSuperclass vs @Inheritance
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Покажите ключевое различие: @MappedSuperclass даёт ПОЛЯ, но НЕ полиморфизм.
 *
 *   1) BaseEntity04 — @MappedSuperclass с полями id (@Id @GeneratedValue) и createdAt.
 *      Product04 extends BaseEntity04 (@Entity), Order04 extends BaseEntity04 (@Entity).
 *      Колонки id/created_at окажутся в КАЖДОЙ таблице (products, orders).
 *   2) Сохраните по объекту каждого типа.
 *   3) Докажите, что полиморфный запрос по базе НЕВОЗМОЖЕН:
 *        - попытка em.createQuery("select b from BaseEntity04 b", BaseEntity04.class)
 *          бросит IllegalArgumentException (BaseEntity04 — не сущность). Поймайте и напечатайте.
 *        - а запросы по конкретным сущностям (Product04/Order04) работают.
 *   4) В комментарии отметьте: для общих полей (аудит) — @MappedSuperclass;
 *      для «is-a» иерархии с полиморфизмом — @Inheritance (Task01–03).
 *
 * ЦЕЛЬ: чётко развести два механизма «общего предка».
 *
 * ПОДСКАЗКА: @MappedSuperclass нельзя использовать в HQL как тип и нельзя сделать целью связи.
 */

import jakarta.persistence.*;
import java.time.Instant;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Product04("Молоко"));
            em.persist(new Order04("ORD-1"));
            em.getTransaction().commit();
            em.clear();

            // TODO: try {
            // TODO:     em.createQuery("select b from BaseEntity04 b", BaseEntity04.class).getResultList();
            // TODO: } catch (IllegalArgumentException e) {
            // TODO:     System.out.println("полиморфно по @MappedSuperclass нельзя: " + e.getClass().getSimpleName());
            // TODO: }
            // TODO: long products = em.createQuery("select count(p) from Product04 p", Long.class).getSingleResult();
            // TODO: long orders   = em.createQuery("select count(o) from Order04 o", Long.class).getSingleResult();
            // TODO: System.out.println("products=" + products + " orders=" + orders); // 1 и 1
        } finally {
            em.close();
            emf.close();
        }
    }
}
