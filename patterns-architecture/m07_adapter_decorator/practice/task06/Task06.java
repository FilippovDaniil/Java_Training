package m07_adapter_decorator.practice.task06;

/**
 * Задача 06 — Тема 07: Adapter + Decorator вместе
 *
 * ЗАДАНИЕ:
 *   Совместите два паттерна: чужой вывод приведите к нужному интерфейсу
 *   (Adapter), а затем нарастите поведение (Decorator).
 *     - чужой класс LegacyConsole (файл LegacyConsole.java, менять НЕЛЬЗЯ):
 *       void writeLine(String s) — печатает строку;
 *     - интерфейс Logger (файл Logger.java): void log(String msg);
 *     - LegacyConsoleAdapter (файл LegacyConsoleAdapter.java): адаптирует
 *       LegacyConsole к Logger (log → writeLine);
 *     - абстрактный LoggerDecorator (файл LoggerDecorator.java) хранит inner;
 *     - LevelDecorator добавляет к сообщению префикс "[INFO] " и передаёт inner.
 *   В main оберните адаптер в LevelDecorator и залогируйте сообщение.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [INFO] система запущена
 *
 * ТРЕБОВАНИЯ:
 *   - LegacyConsole не меняется (доступ только через адаптер);
 *   - Adapter приводит интерфейс, Decorator добавляет префикс (намерения разные);
 *   - декоратор делегирует inner.log(...) с изменённым сообщением.
 *
 * ПОДСКАЗКА:
 *   new LevelDecorator(new LegacyConsoleAdapter(new LegacyConsole())).
 *   Adapter = «совместить интерфейсы», Decorator = «добавить поведение».
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: оберните LegacyConsole адаптером, затем LevelDecorator; вызовите log(...)
    }
}
