package m07_adapter_decorator.practice.task04;

/**
 * Задача 04 — Тема 07: Decorator для обработки текста
 *
 * ЗАДАНИЕ:
 *   Реализуйте цепочку обработчиков текста через декораторы:
 *     - интерфейс TextProcessor (файл TextProcessor.java): String process(String input);
 *     - базовый Identity (файл Identity.java): возвращает вход без изменений;
 *     - абстрактный ProcessorDecorator (файл ProcessorDecorator.java) хранит inner;
 *     - TrimDecorator (обрезает пробелы по краям) и UpperCaseDecorator
 *       (переводит в верхний регистр), каждый сначала зовёт inner.process(...),
 *       затем применяет своё преобразование.
 *   В main обработайте строку "  привет  " цепочкой trim → upper.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Результат: 'ПРИВЕТ'
 *
 * ТРЕБОВАНИЯ:
 *   - декораторы реализуют тот же TextProcessor (можно вкладывать);
 *   - каждый декоратор делегирует inner и потом применяет своё преобразование;
 *   - базовый Identity не теряется в цепочке.
 *
 * ПОДСКАЗКА:
 *   new UpperCaseDecorator(new TrimDecorator(new Identity())). Сначала отрабатывает
 *   самый внутренний (Identity → trim), потом внешний (upper).
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: соберите цепочку trim → upper над Identity, обработайте "  привет  "
    }
}
