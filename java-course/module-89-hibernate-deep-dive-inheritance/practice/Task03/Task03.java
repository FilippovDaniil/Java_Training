/**
 * Задача 03 — Модуль 89: наследование TABLE_PER_CLASS (таблица на конкретный класс)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true — чтобы увидеть UNION при полиморфном запросе.
 *
 * ЗАДАНИЕ:
 *   1) Иерархия Payment03 → CardPayment03/CashPayment03, стратегия TABLE_PER_CLASS:
 *        @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 *      ВАЖНО: IDENTITY несовместима с TABLE_PER_CLASS — используйте
 *        @GeneratedValue(strategy = GenerationType.TABLE) (или SEQUENCE).
 *   2) Колонка amount продублируется в card_payments и cash_payments (нет общей таблицы).
 *   3) Сохраните по платежу; выполните полиморфный "select p from Payment03 p" — в логе
 *      это будет UNION по обеим таблицам. Выведите общее число.
 *
 * ЦЕЛЬ: увидеть третий вариант и его минусы (дублирование колонок, UNION, проблемы с id).
 *
 * ПОДСКАЗКА: TABLE_PER_CLASS применяют редко — обычно выбирают SINGLE_TABLE или JOINED.
 */

import jakarta.persistence.*;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new CardPayment03(1000, "4111111111111111"));
            em.persist(new CashPayment03(500, "Иванов"));
            em.getTransaction().commit();
            em.clear();

            // TODO: long total = em.createQuery("select count(p) from Payment03 p", Long.class)
            // TODO:                 .getSingleResult();   // в логе — UNION двух таблиц
            // TODO: System.out.println("всего платежей (UNION): " + total); // 2
        } finally {
            em.close();
            emf.close();
        }
    }
}
