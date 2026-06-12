package m22_antipatterns.practice.task02;

/**
 * Задача 02 — Тема 22: разбиваем God Object
 *
 * ЗАДАНИЕ:
 *   Дан GodSalesManager (файл GodSalesManager.java) — он и считает сумму продаж,
 *   и форматирует отчёт (несколько несвязанных обязанностей). НЕ используйте его
 *   в решении (это «до»). Разнесите на связные классы:
 *     - SalesCalculator (файл SalesCalculator.java): long total(long[] sales);
 *     - SalesFormatter (файл SalesFormatter.java): String report(long total) →
 *       "Итого продаж: <total>".
 *   В main посчитайте сумму через SalesCalculator и отформатируйте через SalesFormatter.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Итого продаж: 30000
 *
 * ТРЕБОВАНИЯ:
 *   - каждая обязанность — в своём связном классе (расчёт / форматирование);
 *   - GodSalesManager не используется в решении;
 *   - классы не делают «чужую» работу (High Cohesion, SRP).
 *
 * ПОДСКАЗКА:
 *   God Object лечится разнесением по SRP (тема 02) и High Cohesion (тема 14).
 *   Признак God Object: в описании класса несколько несвязанных «И».
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: total через SalesCalculator; report через SalesFormatter
    }
}
