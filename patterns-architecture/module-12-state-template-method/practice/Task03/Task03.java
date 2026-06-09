/**
 * Задача 03 — Тема 12: Template Method (каркас + шаги + hook)
 *
 * ЗАДАНИЕ:
 *   Задайте скелет обработки текста в базовом классе, а шаги — в подклассах:
 *     - абстрактный TextPipeline (файл TextPipeline.java):
 *         public final String run(String input) {
 *             String s = transform(input);
 *             if (addExclaim()) s = s + "!";   // hook
 *             return s;
 *         }
 *         protected abstract String transform(String input);
 *         protected boolean addExclaim() { return false; }   // hook по умолчанию
 *     - UpperPipeline (transform → toUpperCase) и ReversePipeline
 *       (transform → реверс строки; addExclaim переопределён на true).
 *   В main прогоните одну строку через оба конвейера.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Upper: ПРИВЕТ
 *   Reverse: тевирп!
 *
 * ТРЕБОВАНИЯ:
 *   - run() — final (порядок шагов нельзя переопределить);
 *   - transform() реализуют подклассы;
 *   - addExclaim() — hook: переопределяется по желанию (ReversePipeline = true).
 *
 * ПОДСКАЗКА:
 *   Реверс: new StringBuilder(input).reverse().toString().
 *   Каркас один, меняются только шаги — это и есть Template Method.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: прогоните "привет" через UpperPipeline и ReversePipeline, выведите результат
    }
}
