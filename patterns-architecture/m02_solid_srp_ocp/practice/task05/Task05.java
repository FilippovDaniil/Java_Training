package m02_solid_srp_ocp.practice.task05;

/**
 * Задача 05 — Тема 02: SRP + OCP вместе
 *
 * ЗАДАНИЕ:
 *   Считаем итог по цене с налогом и выводим разбивку. Разнесите расчёт и
 *   вывод (SRP) и сделайте налог расширяемым (OCP):
 *     - TaxRule (файл TaxRule.java): long taxCents(long amountCents);
 *       реализации NoTax (0) и VatTax(percent);
 *     - TotalCalculator (файл TotalCalculator.java): принимает TaxRule,
 *       метод long totalWithTax(long base) = base + налог. ТОЛЬКО считает;
 *     - BreakdownRenderer (файл BreakdownRenderer.java): печатает разбивку
 *       (цена, налог, итог). ТОЛЬКО форматирует, ничего не считает сам —
 *       значения получает на вход.
 *   В main посчитайте итог с VatTax(20) и выведите разбивку.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Цена: 10000
 *   Налог: 2000
 *   Итого: 12000
 *
 * ТРЕБОВАНИЯ:
 *   - расчёт и вывод — разные классы (SRP);
 *   - новый вид налога = новый класс TaxRule без правок калькулятора (OCP);
 *   - BreakdownRenderer не считает налог сам, а принимает уже посчитанные суммы.
 *
 * ПОДСКАЗКА:
 *   Это та же связка, что в реальных системах: «сервис считает → представление
 *   рисует». Калькулятор зависит от абстракции TaxRule.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: TotalCalculator с VatTax(20); посчитайте итог;
        //       передайте суммы в BreakdownRenderer для вывода
    }
}
