/**
 * Задача 05 — Тема 19: Domain Event (агрегат фиксирует факты)
 *
 * ЗАДАНИЕ:
 *   Пусть агрегат записывает доменные события (факты в прошедшем времени):
 *     - MoneyDeposited (файл MoneyDeposited.java) — событие-факт: record
 *       (accountId, amountCents) (готов, неизменяем);
 *     - Account (файл Account.java): id, balanceCents; deposit(amount) увеличивает
 *       баланс И добавляет MoneyDeposited в список событий; pullEvents() возвращает
 *       копию накопленных событий и ОЧИЩАЕТ список; balance().
 *   В main: сделайте 2 депозита, заберите события через pullEvents() (их 2),
 *   затем вызовите pullEvents() снова — список пуст (события уже забраны).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Событий после 2 депозитов: 2
 *   Событий при повторном pull: 0
 *
 * ТРЕБОВАНИЯ:
 *   - событие неизменяемо и названо в ПРОШЕДШЕМ времени (MoneyDeposited);
 *   - агрегат записывает событие в момент изменения состояния;
 *   - pullEvents отдаёт копию и очищает внутренний список (события «вытащили»).
 *
 * ПОДСКАЗКА:
 *   pullEvents(): var copy = List.copyOf(events); events.clear(); return copy;
 *   Дальше (в реальной системе) эти события публикуются подписчикам.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: deposit дважды; pullEvents() → размер; pullEvents() снова → 0
    }
}
