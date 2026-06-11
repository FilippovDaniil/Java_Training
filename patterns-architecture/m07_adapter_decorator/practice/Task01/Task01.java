package m07_adapter_decorator.practice.task01;

/**
 * Задача 01 — Тема 07: Adapter (object adapter)
 *
 * ЗАДАНИЕ:
 *   Есть чужой класс LegacyPayment (файл LegacyPayment.java, менять НЕЛЬЗЯ) с
 *   неудобным методом void doPayment(int cents). Клиент же работает с
 *   интерфейсом PaymentGateway (файл PaymentGateway.java): boolean pay(long
 *   amountCents). Напишите адаптер:
 *     - LegacyPaymentAdapter (файл LegacyPaymentAdapter.java) реализует
 *       PaymentGateway, внутри хранит LegacyPayment и делегирует ему вызов,
 *       приводя long → int; возвращает true.
 *   В main создайте адаптер поверх LegacyPayment и оплатите через интерфейс
 *   PaymentGateway.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   оплачено 5000
 *   Результат pay: true
 *
 * ТРЕБОВАНИЯ:
 *   - LegacyPayment не изменяется;
 *   - адаптер реализует PaymentGateway и делегирует чужому классу (композиция);
 *   - адаптер только «переводит» вызов, без бизнес-логики.
 *
 * ПОДСКАЗКА:
 *   Object adapter = реализовать ожидаемый интерфейс и хранить ссылку на
 *   адаптируемый объект, перенаправляя ему методы.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: оберните LegacyPayment в LegacyPaymentAdapter, вызовите pay(...)
    }
}
