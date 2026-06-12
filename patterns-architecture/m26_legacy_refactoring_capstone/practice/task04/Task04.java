package m26_legacy_refactoring_capstone.practice.task04;

/**
 * Задача 04 — Тема 26: Branch by Abstraction
 *
 * ЗАДАНИЕ:
 *   Безопасная замена реализации: вводим абстракцию, клиент зависит от неё, а
 *   реализацию подменяем в точке сборки.
 *     - PaymentProcessor (файл PaymentProcessor.java): boolean pay(long amount);
 *     - LegacyPaymentProcessor (печатает "[legacy] оплата amount", возвращает true)
 *       и ModernPaymentProcessor ("[modern] оплата amount", true);
 *     - CheckoutService (файл CheckoutService.java): зависит от PaymentProcessor
 *       (конструктор); checkout(amount) → pay(...) и возвращает "оплачено"/"отклонено".
 *   В main прогоните CheckoutService сначала с legacy, затем с modern — КОД клиента
 *   один и тот же, меняется лишь внедрённая реализация.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [legacy] оплата 5000
 *   оплачено
 *   [modern] оплата 5000
 *   оплачено
 *
 * ТРЕБОВАНИЯ:
 *   - CheckoutService зависит от абстракции PaymentProcessor, не от реализации;
 *   - подмена legacy → modern не требует правок клиента (только точка сборки);
 *   - обе реализации за одним интерфейсом.
 *
 * ПОДСКАЗКА:
 *   Шаги: (1) ввести интерфейс над текущим кодом, (2) переключить клиентов на него,
 *   (3) написать новую реализацию, (4) подменить. Это DIP (тема 03) как техника миграции.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: new CheckoutService(legacy).checkout(5000); new CheckoutService(modern).checkout(5000)
    }
}
