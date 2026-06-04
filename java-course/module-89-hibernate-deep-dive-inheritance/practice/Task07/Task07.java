/**
 * Задача 07 — Модуль 89: МИНИ-ПРОЕКТ «Иерархия платежей с полиморфной обработкой»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЦЕЛЬ: спроектировать иерархию способов оплаты, осознанно выбрать стратегию наследования
 *       и реализовать полиморфную обработку (отчёт по всем платежам с учётом подтипов).
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Иерархия Payment07 (SINGLE_TABLE + @DiscriminatorColumn) с абстрактным методом
 *        public abstract String describe();   — каждый подкласс описывает себя.
 *      Подклассы (через @DiscriminatorValue):
 *        - CardPayment07("CARD"):  поле cardNumber; describe() → "Карта ****" + последние 4 цифры;
 *        - CashPayment07("CASH"):  поле cashier;    describe() → "Наличные, кассир " + cashier;
 *        - OnlinePayment07("ONLINE"): поле provider; describe() → "Онлайн: " + provider.
 *      (Поля подклассов — nullable, т.к. SINGLE_TABLE.)
 *
 *   2) Засейте по 1–2 платежа каждого типа.
 *
 *   3) report(em): полиморфный запрос "select p from Payment07 p order by p.amount desc";
 *      для каждого вывести amount + p.describe() (полиморфизм Java + наследование сущностей).
 *
 *   4) Аналитика через HQL:
 *        - общая сумма: "select sum(p.amount) from Payment07 p";
 *        - сумма только онлайн: "... where type(p) = OnlinePayment07".
 *
 * АРХИТЕКТУРА:
 *   Payment07 (SINGLE_TABLE)
 *      ├─ CardPayment07   (CARD)
 *      ├─ CashPayment07   (CASH)
 *      └─ OnlinePayment07 (ONLINE)
 *   report() грузит ВСЕ подтипы одним запросом, describe() работает полиморфно.
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   отчёт перечисляет все платежи с корректным описанием подтипа; суммы посчитаны
 *   полиморфно (всего) и по типу (только онлайн).
 *
 * ПОДСКАЗКА: соберите Task01 (SINGLE_TABLE) и Task06 (полиморфные запросы, type()).
 *            Выбор SINGLE_TABLE оправдан: мало полей, важна скорость полиморфного отчёта.
 */

import jakarta.persistence.*;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // TODO: засейте CardPayment07/CashPayment07/OnlinePayment07
            em.getTransaction().commit();
            em.clear();
            // TODO: report(em);
            // TODO: вывести общую сумму и сумму онлайн-платежей
        } finally {
            em.close();
            emf.close();
        }
    }

    static void report(EntityManager em) {
        // TODO: List<Payment07> all = em.createQuery(
        // TODO:     "select p from Payment07 p order by p.amount desc", Payment07.class).getResultList();
        // TODO: all.forEach(p -> System.out.println(p.getAmount() + " — " + p.describe()));
    }
}
