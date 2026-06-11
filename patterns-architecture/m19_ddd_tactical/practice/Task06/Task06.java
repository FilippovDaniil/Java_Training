package m19_ddd_tactical.practice.task06;

/**
 * Задача 06 — Тема 19: публикация Domain Events подписчикам
 *
 * ЗАДАНИЕ:
 *   Зафиксированное событие нужно раздать заинтересованным обработчикам (это
 *   Observer на уровне домена):
 *     - MoneyDeposited (файл ...) — record (accountId, amountCents) (готов);
 *     - EventHandler (файл EventHandler.java): void handle(MoneyDeposited event);
 *     - AuditHandler ("[audit] +amount на accountId") и EmailHandler
 *       ("[email] зачислено amount");
 *     - EventDispatcher (файл EventDispatcher.java): subscribe(EventHandler);
 *       publish(MoneyDeposited) уведомляет ВСЕХ подписчиков.
 *   В main: подпишите оба обработчика, опубликуйте событие — оба реагируют.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [audit] +5000 на A-1
 *   [email] зачислено 5000
 *
 * ТРЕБОВАНИЯ:
 *   - диспетчер не знает конкретных обработчиков (только интерфейс EventHandler);
 *   - publish уведомляет всех подписчиков;
 *   - событие неизменяемо, обработчики реагируют независимо.
 *
 * ПОДСКАЗКА:
 *   Это связка Domain Events + Observer (тема 11). В распределённой системе
 *   диспетчером служит брокер (Kafka, тема 24).
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: EventDispatcher; subscribe Audit и Email; publish(new MoneyDeposited("A-1", 5000))
    }
}
