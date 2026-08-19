package m89_hibernate_deep_dive_inheritance.practice.task07;

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
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // ===== 1. Сохраняем платежи =====
            em.getTransaction().begin();

            em.persist(new CardPayment07(1500, "1234-5678-9012-3456"));
            em.persist(new CardPayment07(2500, "9876-5432-1098-7654"));
            em.persist(new CashPayment07(500, "Иван Петров"));
            em.persist(new CashPayment07(700, "Мария Смирнова"));
            em.persist(new OnlinePayment07(3000, "PayPal"));
            em.persist(new OnlinePayment07(1200, "Stripe"));

            em.getTransaction().commit();
            em.clear();

            // ===== 2. Отчёт =====
            System.out.println("=== ОТЧЁТ ПО ВСЕМ ПЛАТЕЖАМ ===");
            report(em);

            // ===== 3. Аналитика =====
            System.out.println("\n=== АНАЛИТИКА ===");

            Long totalSum = em.createQuery(
                    "select sum(p.amount) from Payment07 p", Long.class
            ).getSingleResult();
            System.out.println("Общая сумма всех платежей: " + totalSum + " ₽");

            Long onlineSum = em.createQuery(
                    "select sum(p.amount) from OnlinePayment07 p", Long.class
            ).getSingleResult();
            System.out.println("Сумма онлайн-платежей: " + onlineSum + " ₽");

            // ===== 4. Статистика по типам (исправленный вариант) =====
            System.out.println("\n=== СТАТИСТИКА ПО ТИПАМ ===");

            // Вариант А: через type(p) + Class
            List<Object[]> stats = em.createQuery(
                    "select type(p), count(p) from Payment07 p group by type(p)", Object[].class
            ).getResultList();

            for (Object[] row : stats) {
                Class<?> clazz = (Class<?>) row[0];  // ← это Class
                Long count = (Long) row[1];
                String name = clazz.getSimpleName().replace("Payment07", "");
                System.out.println("  " + name + ": " + count + " шт.");
            }

            // Альтернативный вариант: отдельные запросы
            // System.out.println("\n=== (альтернативная статистика) ===");
            // long cardCount = em.createQuery("select count(p) from CardPayment07 p", Long.class).getSingleResult();
            // long cashCount = em.createQuery("select count(p) from CashPayment07 p", Long.class).getSingleResult();
            // long onlineCount = em.createQuery("select count(p) from OnlinePayment07 p", Long.class).getSingleResult();
            // System.out.println("  CARD: " + cardCount + " шт.");
            // System.out.println("  CASH: " + cashCount + " шт.");
            // System.out.println("  ONLINE: " + onlineCount + " шт.");

        } finally {
            em.close();
            emf.close();
        }
    }

    static void report(EntityManager em) {
        List<Payment07> all = em.createQuery(
                "select p from Payment07 p order by p.amount desc", Payment07.class
        ).getResultList();

        for (Payment07 p : all) {
            System.out.println(p.getAmount() + " ₽ — " + p.describe());
        }
    }
}