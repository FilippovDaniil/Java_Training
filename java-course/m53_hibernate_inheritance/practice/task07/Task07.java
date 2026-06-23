package m53_hibernate_inheritance.practice.task07;

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
     * STRATEGY_CHOICE: SINGLE_TABLE
     * Обоснование:
     * - Всего 3 подтипа, у каждого не более 4 уникальных полей
     * - Частые полиморфные запросы (findAll, findByStatus)
     * - Нужна производительность при чтении
     * - NULL-поля допустимы, т.к. их немного
     * - Простота запросов без JOIN
     * - Легко добавлять новые типы оплаты
     * - В отличие от TABLE_PER_CLASS, можно делать FK на Payment
     */

    public static void main(String[] args) {
        // Создаем SessionFactory
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:payments;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Payment.class);
        config.addAnnotatedClass(CardPayment.class);
        config.addAnnotatedClass(CashPayment.class);
        config.addAnnotatedClass(CryptoPayment.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== ИЕРАРХИЯ СПОСОБОВ ОПЛАТЫ (SINGLE_TABLE) ===\n");

            PaymentRepository repo = new PaymentRepository(factory);

            // ============================================
            // a) СОХРАНЕНИЕ ТЕСТОВЫХ ДАННЫХ
            // ============================================
            System.out.println("--- a) СОХРАНЕНИЕ ПЛАТЕЖЕЙ ---");

            // Карточные платежи
            CardPayment card1 = new CardPayment(
                    new BigDecimal("5000.00"),
                    "4111 **** **** 1111",
                    "Иван Петров",
                    "12/25"
            );
            card1.setStatus(PaymentStatus.PENDING);

            CardPayment card2 = new CardPayment(
                    new BigDecimal("12500.00"),
                    "5555 **** **** 4444",
                    "Мария Смирнова",
                    "06/26"
            );
            card2.setStatus(PaymentStatus.COMPLETED);
            card2.setAuthorizationCode("AUTH-12345");

            // Наличные платежи
            CashPayment cash1 = new CashPayment(
                    new BigDecimal("800.00"),
                    "ул. Ленина 1",
                    new BigDecimal("200.00")
            );
            cash1.setStatus(PaymentStatus.PENDING);
            cash1.setCourierName("Петр Сидоров");

            CashPayment cash2 = new CashPayment(
                    new BigDecimal("1200.00"),
                    "пр. Мира 5",
                    new BigDecimal("300.00")
            );
            cash2.setStatus(PaymentStatus.COMPLETED);
            cash2.setCourierName("Анна Кузнецова");

            // Криптовалютные платежи
            CryptoPayment crypto1 = new CryptoPayment(
                    new BigDecimal("50000.00"),
                    "bc1q7x4g5h9k2m3n6p8r1s2t3u4v5w6x7y8z9a0b1c2",
                    "BTC",
                    new BigDecimal("100000.00")
            );
            crypto1.setStatus(PaymentStatus.PENDING);

            CryptoPayment crypto2 = new CryptoPayment(
                    new BigDecimal("30000.00"),
                    "0xAB12CD34EF56GH78IJ90KL12MN34OP56QR78ST90",
                    "ETH",
                    new BigDecimal("20000.00")
            );
            crypto2.setStatus(PaymentStatus.COMPLETED);
            crypto2.setTransactionHash("0x9876543210abcdef1234567890abcdef12345678");

            repo.save(card1);
            repo.save(card2);
            repo.save(cash1);
            repo.save(cash2);
            repo.save(crypto1);
            repo.save(crypto2);

            System.out.println("   ✅ Сохранены платежи:");
            System.out.println("      - 2 карточных");
            System.out.println("      - 2 наличных");
            System.out.println("      - 2 криптовалютных");

            // ============================================
            // b) FINDALL() С INSTANCEOF
            // ============================================
            System.out.println("\n--- b) ВСЕ ПЛАТЕЖИ (findAll + instanceof) ---");

            List<Payment> allPayments = repo.findAll();
            System.out.println("   Всего платежей: " + allPayments.size() + "\n");

            for (Payment p : allPayments) {
                if (p instanceof CardPayment card) {
                    System.out.printf("   💳 Карта: %.2f %s, статус: %s, карта: %s%n",
                            card.getAmount(), card.getCurrency(),
                            card.getStatus(), card.getMaskedCardNumber());
                } else if (p instanceof CashPayment cash) {
                    System.out.printf("   💵 Наличные: %.2f %s, статус: %s, адрес: %s%n",
                            cash.getAmount(), cash.getCurrency(),
                            cash.getStatus(), cash.getDeliveryAddress());
                } else if (p instanceof CryptoPayment crypto) {
                    System.out.printf("   🪙 Криптовалюта: %.2f %s, статус: %s, валюта: %s%n",
                            crypto.getAmount(), crypto.getCurrency(),
                            crypto.getStatus(), crypto.getCryptoCurrency());
                }
            }

            // ============================================
            // c) FIND BY STATUS (PENDING)
            // ============================================
            System.out.println("\n--- c) ПЛАТЕЖИ СО СТАТУСОМ PENDING ---");

            List<Payment> pendingPayments = repo.findByStatus(PaymentStatus.PENDING);
            System.out.println("   Найдено платежей в статусе PENDING: " + pendingPayments.size());
            BigDecimal totalPending = pendingPayments.stream()
                    .map(Payment::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.printf("   Общая сумма: %.2f %s%n", totalPending, "RUB");

            // ============================================
            // d) FIND ALL CARD PAYMENTS
            // ============================================
            System.out.println("\n--- d) ВСЕ КАРТОЧНЫЕ ПЛАТЕЖИ ---");

            List<CardPayment> cardPayments = repo.findAllCardPayments();
            System.out.println("   Карточных платежей: " + cardPayments.size());
            for (CardPayment card : cardPayments) {
                System.out.printf("      - %s, сумма: %.2f %s, статус: %s%n",
                        card.getMaskedCardNumber(),
                        card.getAmount(), card.getCurrency(),
                        card.getStatus());
            }

            // ============================================
            // e) COUNT BY TYPE
            // ============================================
            System.out.println("\n--- e) СТАТИСТИКА ПО ТИПАМ ---");

            Map<String, Long> typeCounts = repo.countByType();
            System.out.println("   Распределение платежей по типам:");
            typeCounts.forEach((type, count) ->
                    System.out.printf("      %s: %d шт.%n", type, count)
            );

            // ============================================
            // f) UPDATE STATUS
            // ============================================
            System.out.println("\n--- f) ОБНОВЛЕНИЕ СТАТУСА ---");

            Long paymentId = 1L;
            System.out.printf("   Обновление статуса платежа ID=%d: PENDING → COMPLETED%n", paymentId);

            repo.updateStatus(paymentId, PaymentStatus.COMPLETED);

            Optional<Payment> updated = repo.findById(paymentId);
            updated.ifPresentOrElse(
                    p -> System.out.printf("   ✅ Статус обновлен: %s%n", p.getStatus()),
                    () -> System.out.println("   ❌ Платеж не найден")
            );

            // ============================================
            // ДОПОЛНИТЕЛЬНО: ФИЛЬТРАЦИЯ ПО СТАТУСУ COMPLETED
            // ============================================
            System.out.println("\n--- ДОПОЛНИТЕЛЬНО: ПЛАТЕЖИ СО СТАТУСОМ COMPLETED ---");

            List<Payment> completedPayments = repo.findByStatus(PaymentStatus.COMPLETED);
            System.out.println("   Завершенных платежей: " + completedPayments.size());
            BigDecimal totalCompleted = completedPayments.stream()
                    .map(Payment::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.printf("   Общая сумма: %.2f %s%n", totalCompleted, "RUB");

            // ============================================
            // ИТОГИ
            // ============================================
            System.out.println("\n--- ИТОГИ ---");
            System.out.printf("   Всего платежей: %d%n", repo.findAll().size());
            System.out.printf("   Общая сумма: %.2f %s%n",
                    repo.findAll().stream()
                            .map(Payment::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add),
                    "RUB");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
