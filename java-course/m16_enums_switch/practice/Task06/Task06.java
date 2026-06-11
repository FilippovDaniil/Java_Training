package m16_enums_switch.practice.task06;

/**
 * Задача 06 — Модуль 16: Singleton через enum
 *
 * ЗАДАНИЕ:
 *   Реализуйте логгер-синглтон через enum с единственной константой
 *   INSTANCE. У него:
 *     - private поле count (счётчик записей);
 *     - метод log(String message), который увеличивает счётчик и
 *       выводит "[N] message".
 *   В main несколько раз вызовите Logger.INSTANCE.log(...) и убедитесь,
 *   что счётчик общий (это один и тот же объект).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [1] Старт приложения
 *   [2] Загрузка данных
 *   [3] Готово
 *
 * ПОДСКАЗКА:
 *   enum Logger { INSTANCE; private int count; public void log(String m){...} }
 *   Это потокобезопасный Singleton, гарантированный JVM.
 */

public class Task06 {
    public static void main(String[] args) {
        // Вызовите Logger.INSTANCE.log(...) несколько раз
    }
}
