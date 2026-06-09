/**
 * Задача 06 — Тема 03: DIP — подмена реализации без правок потребителя
 *
 * ЗАДАНИЕ:
 *   Покажите главную выгоду DIP: одну и ту же бизнес-логику гоняем с разными
 *   реализациями зависимости, не меняя её код.
 *     - интерфейс MessageSender (файл MessageSender.java): void send(String msg);
 *     - ConsoleSender — печатает в консоль (боевая реализация);
 *     - CollectingSender — складывает сообщения в список (для тестов/проверки),
 *       метод sentCount();
 *     - AlertService (файл AlertService.java): получает MessageSender в
 *       конструкторе, метод raise(String reason) шлёт "ALERT: " + reason.
 *   В main: запустите AlertService сначала с ConsoleSender, затем с
 *   CollectingSender и выведите, сколько алертов он собрал.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   ALERT: диск заполнен
 *   Собрано алертов в тесте: 2
 *
 * ТРЕБОВАНИЯ:
 *   - AlertService не меняется при смене отправителя;
 *   - CollectingSender доказывает тестируемость: проверяем поведение сервиса
 *     без реальной отправки;
 *   - обе реализации взаимозаменяемы (это и LSP тоже).
 *
 * ПОДСКАЗКА:
 *   Возможность подставить «тестового двойника» вместо боевой зависимости —
 *   прямое следствие DIP. В теме 16 это делает DI-контейнер автоматически.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: AlertService с ConsoleSender → raise(...);
        //       AlertService с CollectingSender → несколько raise(...),
        //       вывести sentCount()
    }
}
