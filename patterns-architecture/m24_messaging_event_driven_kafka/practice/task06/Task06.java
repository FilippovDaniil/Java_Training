package m24_messaging_event_driven_kafka.practice.task06;

/**
 * Задача 06 — Тема 24: Retry + DLQ
 *
 * ЗАДАНИЕ:
 *   Перед отправкой в DLQ сообщение повторяют несколько раз:
 *     - RetryingProcessor (файл RetryingProcessor.java): maxAttempts = 3; список DLQ;
 *       метод process(String msg, int failuresBeforeSuccess):
 *         перебирает попытки 1..maxAttempts;
 *         если номер попытки <= failuresBeforeSuccess → печатает "ошибка [msg] попытка N";
 *         иначе печатает "успех [msg] попытка N" и завершает;
 *         если все попытки неудачны → кладёт в DLQ и печатает "в DLQ [msg]";
 *       dlqSize().
 *   В main: process("m1", 1) (упадёт 1 раз, потом успех); process("m2", 5)
 *   (падает все 3 попытки → DLQ); выведите размер DLQ.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   ошибка [m1] попытка 1
 *   успех [m1] попытка 2
 *   ошибка [m2] попытка 1
 *   ошибка [m2] попытка 2
 *   ошибка [m2] попытка 3
 *   в DLQ [m2]
 *   В DLQ: 1
 *
 * ТРЕБОВАНИЯ:
 *   - повтор до maxAttempts раз; успех завершает повторы досрочно;
 *   - после исчерпания попыток сообщение уходит в DLQ (не крутится вечно);
 *   - DLQ хранит окончательно «ядовитые» сообщения.
 *
 * ПОДСКАЗКА:
 *   failuresBeforeSuccess — сколько первых попыток «падает» (для детерминированности,
 *   без случайности). Цикл: for (attempt=1; attempt<=maxAttempts; attempt++).
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: process("m1", 1); process("m2", 5); вывести dlqSize()
    }
}
