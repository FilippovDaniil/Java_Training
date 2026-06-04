/**
 * Задача 06 — Модуль 89: полиморфные запросы — type() и treat()
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Иерархия Payment06 (SINGLE_TABLE) → CardPayment06/CashPayment06 дана готовой.
 *   Засейте 2 карточных и 1 наличный платёж. Затем:
 *   1) Полиморфный запрос: "select p from Payment06 p" → вернёт все 3 (и Card, и Cash).
 *      Выведите количество и типы.
 *   2) Фильтр по типу: "select p from Payment06 p where type(p) = CardPayment06"
 *      → только карточные (2 штуки).
 *   3) Доступ к полю подкласса через treat:
 *      "select p from Payment06 p where treat(p as CardPayment06).cardNumber like '4%'"
 *      → карты, номер которых начинается с 4.
 *
 * ЦЕЛЬ: освоить полиморфные запросы и приведение типов в HQL.
 *
 * ПОДСКАЗКА: type(p) = ПодКласс — фильтр по классу; treat(p as ПодКласс).поле — доступ к полю подтипа.
 */

import jakarta.persistence.*;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new CardPayment06(1000, "4111111111111111"));
            em.persist(new CardPayment06(2000, "5500000000000004")); // начинается с 5
            em.persist(new CashPayment06(500, "Иванов"));
            em.getTransaction().commit();
            em.clear();

            // 1) полиморфно
            // TODO: List<Payment06> all = em.createQuery("select p from Payment06 p", Payment06.class).getResultList();
            // TODO: System.out.println("всего: " + all.size()); // 3
            // TODO: all.forEach(p -> System.out.println(p.getClass().getSimpleName()));

            // 2) фильтр по типу
            // TODO: Long cards = em.createQuery(
            // TODO:     "select count(p) from Payment06 p where type(p) = CardPayment06", Long.class)
            // TODO:     .getSingleResult();
            // TODO: System.out.println("карточных: " + cards); // 2

            // 3) treat — доступ к полю подкласса
            // TODO: List<Payment06> visa = em.createQuery(
            // TODO:     "select p from Payment06 p where treat(p as CardPayment06).cardNumber like '4%'", Payment06.class)
            // TODO:     .getResultList();
            // TODO: System.out.println("карт на '4...': " + visa.size()); // 1
        } finally {
            em.close();
            emf.close();
        }
    }
}
