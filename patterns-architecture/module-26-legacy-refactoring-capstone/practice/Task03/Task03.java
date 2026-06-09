/**
 * Задача 03 — Тема 26: Parallel Run (сверка старого и нового)
 *
 * ЗАДАНИЕ:
 *   Перед переключением гоняем оба варианта и сверяем; источник истины пока — старое:
 *     - Calculator (файл Calculator.java): long calc(long input);
 *     - LegacyCalculator (input * 2) и ModernCalculator (input * 2, НО для input==13
 *       ошибочно возвращает input * 3 — внесённое расхождение);
 *     - ParallelRunner (файл ParallelRunner.java): хранит legacy и modern;
 *       run(input) считает оба; если результаты различаются — печатает
 *       "расхождение на <input>: legacy=<l>, modern=<m>"; ВОЗВРАЩАЕТ результат legacy.
 *   В main: прогоните input=10 (совпадение) и input=13 (расхождение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Результат (10): 20
 *   расхождение на 13: legacy=26, modern=39
 *   Результат (13): 26
 *
 * ТРЕБОВАНИЯ:
 *   - считаются ОБА варианта на одних данных;
 *   - расхождение логируется, но источником истины остаётся legacy;
 *   - при совпадении лога нет.
 *
 * ПОДСКАЗКА:
 *   Parallel Run выявляет, где новое расходится со старым, ещё ДО переключения.
 *   Возвращаем legacy, пока не убедились, что modern корректен.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: ParallelRunner(legacy, modern); run(10) и run(13), печатая результат
    }
}
