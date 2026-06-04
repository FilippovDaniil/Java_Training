/**
 * Задача 02 — Модуль 89: наследование JOINED (таблица на подкласс)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true — чтобы увидеть JOIN при чтении подкласса.
 *
 * ЗАДАНИЕ:
 *   1) Та же иерархия Payment02 → CardPayment02/CashPayment02, но стратегия JOINED:
 *        @Inheritance(strategy = InheritanceType.JOINED)
 *      (дискриминатор не обязателен).
 *   2) Здесь колонки подклассов МОГУТ быть NOT NULL — пометьте cardNumber и cashier
 *      @Column(nullable = false) (в JOINED это допустимо, т.к. отдельные таблицы).
 *   3) Сохраните по платежу каждого типа, перечитайте; в логе show_sql обратите внимание,
 *      что чтение CardPayment02 идёт с JOIN базовой и дочерней таблиц.
 *
 * ЦЕЛЬ: сравнить JOINED с SINGLE_TABLE (Task01): нормализация и NOT NULL ценой JOIN'ов.
 *
 * ПОДСКАЗКА: схема — payments(id, amount) + card_payments(id→payments, card_number) +
 *            cash_payments(id→payments, cashier).
 */
import jakarta.persistence.*;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long cardId;
        try {
            em.getTransaction().begin();
            CardPayment02 card = new CardPayment02(1000, "4111111111111111");
            CashPayment02 cash = new CashPayment02(500, "Иванов");
            em.persist(card);
            em.persist(cash);
            em.getTransaction().commit();
            cardId = card.getId();
            em.clear();

            // TODO: CardPayment02 c = em.find(CardPayment02.class, cardId);  // в логе — JOIN
            // TODO: System.out.println("карта: " + c.getAmount());
            // TODO: long total = em.createQuery("select count(p) from Payment02 p", Long.class).getSingleResult();
            // TODO: System.out.println("всего платежей: " + total); // 2
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "payments")
// TODO: @Inheritance(strategy = InheritanceType.JOINED)
abstract class Payment02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;
    protected Payment02() {}
    protected Payment02(long amount) { this.amount = amount; }
    public Long getId() { return id; }
    public long getAmount() { return amount; }
}

@Entity @Table(name = "card_payments")
class CardPayment02 extends Payment02 {
    // TODO: @Column(nullable = false)  // в JOINED это можно
    private String cardNumber;
    protected CardPayment02() {}
    public CardPayment02(long amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
}

@Entity @Table(name = "cash_payments")
class CashPayment02 extends Payment02 {
    // TODO: @Column(nullable = false)
    private String cashier;
    protected CashPayment02() {}
    public CashPayment02(long amount, String cashier) { super(amount); this.cashier = cashier; }
}
