package m17_exceptions.practice;

/**
 * Задача 05 — Модуль 17: throws (проброс исключения)
 *
 * ЗАДАНИЕ:
 *   Напишите метод int parsePositive(String s) throws Exception,
 *   который преобразует строку в число и:
 *     - бросает Exception("Число должно быть положительным"),
 *       если число <= 0;
 *     - пробрасывает NumberFormatException (не ловит сам),
 *       если строка не число.
 *   В main вызовите метод для нескольких строк, обработав исключения
 *   через try-catch.
 *
 * ПРИМЕРЫ:
 *   "42"  → 42
 *   "-5"  → перехват: "Число должно быть положительным"
 *   "abc" → перехват NumberFormatException
 *
 * ПОДСКАЗКА:
 *   Метод объявляет throws Exception — значит, вызывающий обязан
 *   обработать или пробросить дальше.
 */
public class Task05 {
    public static void main(String[] args) {
        // Вызовите parsePositive для "42", "-5", "abc" в try-catch
    }

    // TODO: метод parsePositive(String s) throws Exception
}
