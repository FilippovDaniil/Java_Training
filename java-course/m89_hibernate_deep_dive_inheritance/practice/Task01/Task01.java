package m89_hibernate_deep_dive_inheritance.practice.task01;

/**
 * Задача 01 — Модуль 89: наследование SINGLE_TABLE + дискриминатор
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Сделайте Payment01 абстрактной @Entity со стратегией SINGLE_TABLE:
 *        @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 *        @DiscriminatorColumn(name = "payment_type")
 *   2) CardPayment01 @DiscriminatorValue("CARD") (поле cardNumber);
 *      CashPayment01 @DiscriminatorValue("CASH") (поле cashier).
 *   3) Сохраните по одному платежу каждого типа. Они лягут в ОДНУ таблицу payments,
 *      различаясь колонкой payment_type.
 *   4) Перечитайте оба через em.find(Payment01.class, id) и выведите их getClass()
 *      — Hibernate вернёт правильный подтип.
 *
 * ЦЕЛЬ: освоить SINGLE_TABLE; понять, что card_number/cashier обязаны быть nullable.
 *
 * ПОДСКАЗКА: в одной таблице у CASH-строки card_number = NULL, у CARD-строки cashier = NULL —
 *            поэтому NOT NULL на этих колонках поставить нельзя.
 */

import jakarta.persistence.*;

public class Task01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long cardId, cashId;
        try {
            em.getTransaction().begin();
            CardPayment01 card = new CardPayment01(1000, "4111111111111111");
            CashPayment01 cash = new CashPayment01(500, "Иванов");
            em.persist(card);
            em.persist(cash);
            em.getTransaction().commit();
            cardId = card.getId();
            cashId = cash.getId();
            em.clear();

            // TODO: System.out.println(em.find(Payment01.class, cardId).getClass().getSimpleName()); // CardPayment01
            // TODO: System.out.println(em.find(Payment01.class, cashId).getClass().getSimpleName()); // CashPayment01
        } finally {
            em.close();
            emf.close();
        }
    }
}
