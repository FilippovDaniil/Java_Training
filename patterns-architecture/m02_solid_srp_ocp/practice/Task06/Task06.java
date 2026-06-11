package m02_solid_srp_ocp.practice.task06;

/**
 * Задача 06 — Тема 02: рефакторинг нарушения SRP + OCP
 *
 * ЗАДАНИЕ:
 *   Дан LegacyNotifier (файл LegacyNotifier.java) — рабочий, но «больной»:
 *     - switch (channel) по типу канала → нарушение OCP (новый канал = правка);
 *     - сам и форматирует текст, и «отправляет» → нарушение SRP.
 *   Перепишите его в чистый вид (LegacyNotifier НЕ используйте в main, он
 *   оставлен как «до»):
 *     - интерфейс Channel (файл Channel.java): String name(); void send(String text);
 *     - EmailChannel и SmsChannel реализуют Channel (каждый шлёт по-своему);
 *     - Notifier (файл Notifier.java): принимает Channel в конструкторе и
 *       метод notify(String text) делегирует отправку каналу — БЕЗ switch.
 *   В main отправьте одно сообщение через EmailChannel и через SmsChannel.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [email] Привет
 *   [sms] Привет
 *
 * ТРЕБОВАНИЯ:
 *   - в новом коде нет switch/if по типу канала;
 *   - добавление третьего канала (например, push) не требует правок Notifier;
 *   - форматирование/отправка инкапсулированы в самом канале (SRP).
 *
 * ПОДСКАЗКА:
 *   Сравните количество мест, которые пришлось бы менять для нового канала:
 *   в LegacyNotifier — внутри switch; в новой схеме — ноль (просто новый класс).
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: создайте Notifier с EmailChannel и с SmsChannel, отправьте текст
    }
}
