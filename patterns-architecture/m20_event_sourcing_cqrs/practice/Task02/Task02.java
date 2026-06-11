package m20_event_sourcing_cqrs.practice.task02;

/**
 * Задача 02 — Тема 20: Event Sourcing — запись новых событий
 *
 * ЗАДАНИЕ:
 *   Сторона записи: операции не «меняют поле», а ПОРОЖДАЮТ событие, которое
 *   применяется и дописывается в лог.
 *     - события MoneyDeposited(amount), MoneyWithdrawn(amount) (records, готовы);
 *     - Account (файл Account.java): балансовое поле + список changes (лог);
 *       приватный apply(Object) меняет баланс; deposit(amount)/withdraw(amount)
 *       создают событие, вызывают apply и добавляют событие в changes
 *       (withdraw — только если хватает средств); balance(); changes().
 *   В main: deposit 100, withdraw 30, deposit 50; выведите баланс и число событий.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Баланс: 120
 *   Событий в логе: 3
 *
 * ТРЕБОВАНИЯ:
 *   - каждая операция порождает событие и применяет его (apply) к состоянию;
 *   - события дописываются в лог (append-only), не перезаписываются;
 *   - withdraw при нехватке средств не создаёт событие (IllegalStateException).
 *
 * ПОДСКАЗКА:
 *   deposit(a): var e = new MoneyDeposited(a); apply(e); changes.add(e);
 *   Так write-сторона накапливает поток событий — основу Event Sourcing.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: deposit/withdraw/deposit; выведите balance() и changes().size()
    }
}
