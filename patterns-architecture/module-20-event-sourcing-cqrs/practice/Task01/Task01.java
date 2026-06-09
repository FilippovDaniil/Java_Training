/**
 * Задача 01 — Тема 20: Event Sourcing — восстановление из лога
 *
 * ЗАДАНИЕ:
 *   Состояние = свёртка событий. Восстановите баланс, проиграв поток событий:
 *     - события: MoneyDeposited(amount) и MoneyWithdrawn(amount) — records (готовы);
 *     - Account (файл Account.java): поле balance; void apply(Object event)
 *       (Deposited → +amount, Withdrawn → -amount); static Account replay(List<Object>
 *       events) — создаёт счёт и применяет все события; balance().
 *   В main соберите лог [Deposited 100, Withdrawn 30, Deposited 50], восстановите
 *   счёт через replay и выведите баланс.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Баланс после replay: 120
 *
 * ТРЕБОВАНИЯ:
 *   - текущее состояние НЕ хранится отдельно — оно вычисляется из событий;
 *   - apply(event) определяет, как каждое событие меняет состояние;
 *   - replay проигрывает лог с нуля.
 *
 * ПОДСКАЗКА:
 *   if (event instanceof MoneyDeposited e) balance += e.amount();
 *   replay: Account a = new Account(); for (e : events) a.apply(e); return a;
 */

import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        // TODO: соберите List<Object> событий, replay, выведите balance()
    }
}
