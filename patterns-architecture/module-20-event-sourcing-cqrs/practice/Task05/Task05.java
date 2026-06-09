/**
 * Задача 05 — Тема 20: Command handler + Event Store
 *
 * ЗАДАНИЕ:
 *   Сторона записи: обработчик принимает КОМАНДУ (намерение), проверяет её и
 *   дописывает СОБЫТИЕ (факт) в хранилище событий.
 *     - DepositCommand(accountId, amount) — команда (повелительно), record;
 *     - MoneyDeposited(accountId, amount) — событие (прошедшее время), record;
 *     - EventStore (файл EventStore.java): append(Object event); List<Object> events();
 *     - AccountCommandHandler (файл AccountCommandHandler.java): handle(DepositCommand):
 *       если amount <= 0 → IllegalArgumentException (команда отклонена); иначе
 *       append новый MoneyDeposited в store.
 *   В main: обработайте две валидные команды и одну с amount=0 (поймайте исключение);
 *   выведите число событий в store.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Команда отклонена: сумма должна быть > 0
 *   Событий в хранилище: 2
 *
 * ТРЕБОВАНИЯ:
 *   - команда может быть ОТКЛОНЕНА (проверка на write-стороне);
 *   - в хранилище попадают только события успешно обработанных команд;
 *   - событие неизменяемо и в прошедшем времени, команда — в повелительном.
 *
 * ПОДСКАЗКА:
 *   Команда = «сделай» (может не пройти). Событие = «уже произошло» (в логе).
 *   handler.handle проверяет команду и порождает событие.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: EventStore + AccountCommandHandler; handle двух валидных и одной (amount=0);
        //       выведите events().size()
    }
}
