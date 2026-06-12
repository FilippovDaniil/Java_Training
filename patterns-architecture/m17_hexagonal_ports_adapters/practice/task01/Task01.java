package m17_hexagonal_ports_adapters.practice.task01;

/**
 * Задача 01 — Тема 17: выходной порт (driven) + адаптер
 *
 * ЗАДАНИЕ:
 *   Ядро объявляет, ЧТО ему нужно от внешнего мира (выходной порт), а адаптер
 *   это реализует:
 *     - NotificationPort (файл NotificationPort.java) — выходной порт: void send(String msg);
 *     - ConsoleNotificationAdapter (файл ...) — адаптер: печатает "уведомление: msg";
 *     - AlertService (файл AlertService.java) — ЯДРО: зависит от NotificationPort
 *       (конструктор), метод raise(reason) шлёт "ALERT: " + reason через порт.
 *   В main подключите адаптер к ядру и поднимите алерт.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   уведомление: ALERT: диск заполнен
 *
 * ТРЕБОВАНИЯ:
 *   - AlertService (ядро) знает только порт NotificationPort, не адаптер;
 *   - адаптер реализует порт (зависимость направлена внутрь, на интерфейс);
 *   - порт NotificationPort «принадлежит» ядру (его определяет потребитель).
 *
 * ПОДСКАЗКА:
 *   Выходной порт ядро ИСПОЛЬЗУЕТ; адаптер (консоль, в проде — email/SMS) его
 *   реализует. Замена адаптера не трогает ядро.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: AlertService с ConsoleNotificationAdapter; raise("диск заполнен")
    }
}
