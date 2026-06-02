/**
 * Задача 07 — Модуль 53: МИНИ-ПРОЕКТ — иерархия способов оплаты
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Реализуйте полноценную иерархию способов оплаты для интернет-магазина.
 *   Структура:
 *     Payment (abstract, корень иерархии)
 *       ├── CardPayment   — оплата картой
 *       ├── CashPayment   — наличными (при получении)
 *       └── CryptoPayment — криптовалютой
 *
 *   ТРЕБОВАНИЯ:
 *
 *   1) Payment — поля:
 *      - id (Long, @GeneratedValue IDENTITY)
 *      - amount (BigDecimal, обязательное)
 *      - currency (String, по умолчанию "RUB")
 *      - status (enum PaymentStatus: PENDING, COMPLETED, FAILED, REFUNDED)
 *      - createdAt (LocalDateTime, auto)
 *
 *   2) CardPayment — дополнительные поля:
 *      - maskedCardNumber (String, например "4111 **** **** 1111")
 *      - cardHolderName (String)
 *      - expiryDate (String, формат "MM/YY")
 *      - authorizationCode (String, заполняется при успехе)
 *
 *   3) CashPayment — дополнительные поля:
 *      - courierName (String)
 *      - deliveryAddress (String)
 *      - changeRequired (BigDecimal, сдача)
 *
 *   4) CryptoPayment — дополнительные поля:
 *      - walletAddress (String)
 *      - cryptoCurrency (String, например "BTC", "ETH")
 *      - transactionHash (String, заполняется при успехе)
 *      - exchangeRate (BigDecimal, курс на момент оплаты)
 *
 *   5) Выберите ОДНУ стратегию (SINGLE_TABLE или JOINED — обоснуйте выбор в комментарии STRATEGY_CHOICE).
 *
 *   6) Реализуйте PaymentRepository со следующими методами:
 *      - save(Payment p)
 *      - findById(Long id) → Optional<Payment>
 *      - findAll() → List<Payment>
 *      - findByStatus(PaymentStatus status) → List<Payment>
 *      - findAllCardPayments() → List<CardPayment>
 *      - countByType() → Map<String, Long>  (через JPQL GROUP BY TYPE)
 *      - updateStatus(Long id, PaymentStatus newStatus)
 *
 *   7) В main() продемонстрируйте:
 *      a) сохранение по 2 объекта каждого типа
 *      b) findAll() и instanceof-обработку
 *      c) findByStatus(PENDING)
 *      d) findAllCardPayments()
 *      e) countByType() — вывести статистику
 *      f) updateStatus(1L, COMPLETED) и перечитать объект
 *
 * ПОДСКАЗКА:
 *   STRATEGY_CHOICE — запишите здесь свой выбор и обоснование перед реализацией.
 *
 *   enum PaymentStatus можно сохранять как строку:
 *     @Enumerated(EnumType.STRING)
 *     private PaymentStatus status;
 *
 *   findByStatus через JPQL:
 *     "FROM Payment p WHERE p.status = :status"
 *
 *   countByType:
 *     "SELECT TYPE(p), COUNT(p) FROM Payment p GROUP BY TYPE(p)"
 *     Результат: List<Object[]>, где [0] = Class, [1] = Long
 *
 *   updateStatus через JPQL UPDATE:
 *     "UPDATE Payment p SET p.status = :status WHERE p.id = :id"
 *     — не забудьте session.beginTransaction() и transaction.commit()!
 *
 *   PaymentRepository должен принимать SessionFactory в конструктор.
 */
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Task07 {

    /*
     * STRATEGY_CHOICE: выбранная стратегия наследования: [???]
     * Обоснование: TODO
     */

    public static void main(String[] args) {
        // TODO: создайте SessionFactory с H2 in-memory
        //   Зарегистрируйте: Payment, CardPayment, CashPayment, CryptoPayment
        //   Включите show_sql=true и format_sql=true

        // TODO: создайте PaymentRepository(sessionFactory)

        // a) TODO: сохраните тестовые данные:
        //   CardPayment: 5000.00 RUB, карта 4111 **** **** 1111, PENDING
        //   CardPayment: 12500.00 RUB, карта 5555 **** **** 4444, COMPLETED
        //   CashPayment: 800.00 RUB, адрес "ул. Ленина 1", PENDING
        //   CashPayment: 1200.00 RUB, адрес "пр. Мира 5", COMPLETED
        //   CryptoPayment: 0.005 BTC (50000.00 RUB), кошелёк "bc1q...", PENDING
        //   CryptoPayment: 1.5 ETH (30000.00 RUB), кошелёк "0xAB...", COMPLETED

        // b) TODO: вызовите findAll(), переберите результат через instanceof,
        //    выведите тип и сумму каждого платежа

        // c) TODO: вызовите findByStatus(PaymentStatus.PENDING)
        //    выведите количество и суммы

        // d) TODO: вызовите findAllCardPayments()
        //    выведите maskedCardNumber каждой карты

        // e) TODO: вызовите countByType()
        //    выведите: "CardPayment: X шт., CashPayment: Y шт., CryptoPayment: Z шт."

        // f) TODO: вызовите updateStatus(1L, PaymentStatus.COMPLETED)
        //    затем findById(1L) и убедитесь, что статус изменился
    }
}

// ============================================================
// Enum статуса платежа
// ============================================================

enum PaymentStatus {
    PENDING, COMPLETED, FAILED, REFUNDED
}

// ============================================================
// Базовый класс иерархии (дополните аннотациями @Entity @Inheritance)
// ============================================================

@Entity
// TODO: @Table(name = "payments") если SINGLE_TABLE; оставьте только аннотации корня
// TODO: @Inheritance(strategy = ...) — SINGLE_TABLE или JOINED
// TODO: если SINGLE_TABLE — добавьте @DiscriminatorColumn
abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency = "RUB";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // TODO: конструктор(amount, currency, status), геттеры, toString()
}

// ============================================================
// CardPayment (дополните @Entity и уникальными полями)
// ============================================================

@Entity
// TODO: @Table(name = "card_payments") — только если JOINED
// TODO: @DiscriminatorValue("CARD") — только если SINGLE_TABLE
class CardPayment extends Payment {
    @Column(name = "masked_card_number")
    private String maskedCardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "authorization_code")
    private String authorizationCode; // заполняется при успехе

    // TODO: конструктор(amount, maskedCardNumber, cardHolderName, expiryDate), геттеры, toString()
}

// ============================================================
// CashPayment (дополните @Entity и уникальными полями)
// ============================================================

@Entity
// TODO: @Table(name = "cash_payments") — только если JOINED
// TODO: @DiscriminatorValue("CASH") — только если SINGLE_TABLE
class CashPayment extends Payment {
    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "change_required")
    private BigDecimal changeRequired;

    // TODO: конструктор(amount, deliveryAddress, changeRequired), геттеры, toString()
}

// ============================================================
// CryptoPayment (дополните @Entity и уникальными полями)
// ============================================================

@Entity
// TODO: @Table(name = "crypto_payments") — только если JOINED
// TODO: @DiscriminatorValue("CRYPTO") — только если SINGLE_TABLE
class CryptoPayment extends Payment {
    @Column(name = "wallet_address")
    private String walletAddress;

    @Column(name = "crypto_currency")
    private String cryptoCurrency; // BTC, ETH, USDT, ...

    @Column(name = "transaction_hash")
    private String transactionHash; // заполняется при успехе

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate; // курс крипты к RUB на момент оплаты

    // TODO: конструктор(amount, walletAddress, cryptoCurrency, exchangeRate), геттеры, toString()
}

// ============================================================
// PaymentRepository — репозиторий (каркас с TODO)
// ============================================================

class PaymentRepository {

    private final SessionFactory sessionFactory;

    PaymentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /** Сохраняет новый платёж в транзакции */
    public void save(Payment payment) {
        // TODO: открыть сессию, начать транзакцию, persist, commit
    }

    /** Загружает платёж по id; возвращает Optional.empty() если не найден */
    public Optional<Payment> findById(Long id) {
        // TODO: session.get(Payment.class, id) → Optional.ofNullable(...)
        return Optional.empty();
    }

    /** Загружает все платежи (полиморфный запрос) */
    public List<Payment> findAll() {
        // TODO: JPQL "FROM Payment"
        return List.of();
    }

    /** Загружает платежи по статусу */
    public List<Payment> findByStatus(PaymentStatus status) {
        // TODO: JPQL "FROM Payment p WHERE p.status = :status"
        return List.of();
    }

    /** Загружает только карточные платежи */
    public List<CardPayment> findAllCardPayments() {
        // TODO: JPQL "FROM CardPayment"
        return List.of();
    }

    /**
     * Возвращает карту: имя класса → количество.
     * Используйте JPQL: "SELECT TYPE(p), COUNT(p) FROM Payment p GROUP BY TYPE(p)"
     */
    public Map<String, Long> countByType() {
        // TODO: выполните запрос, преобразуйте List<Object[]> в Map<String, Long>
        //       Object[0] = Class (простое имя через getSimpleName()), Object[1] = Long count
        return Map.of();
    }

    /** Обновляет статус платежа через JPQL UPDATE */
    public void updateStatus(Long id, PaymentStatus newStatus) {
        // TODO: JPQL "UPDATE Payment p SET p.status = :status WHERE p.id = :id"
        //       executeUpdate() требует явной транзакции!
    }
}
