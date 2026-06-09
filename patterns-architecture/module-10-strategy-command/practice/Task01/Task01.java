/**
 * Задача 01 — Тема 10: Strategy (взаимозаменяемый алгоритм)
 *
 * ЗАДАНИЕ:
 *   Реализуйте расчёт цены с подменяемой стратегией скидки:
 *     - интерфейс DiscountStrategy (файл DiscountStrategy.java): long apply(long priceCents);
 *     - NoDiscount (без изменений) и PercentDiscount(percent) (минус N%);
 *     - Checkout (файл Checkout.java) — контекст: хранит DiscountStrategy,
 *       метод setStrategy(...) и long total(long price) делегирует стратегии.
 *   В main посчитайте одну цену сначала без скидки, затем сменив стратегию на -20%.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Без скидки: 10000
 *   После -20%: 8000
 *
 * ТРЕБОВАНИЯ:
 *   - смена алгоритма — через setStrategy (без if/switch по типу скидки);
 *   - Checkout работает с интерфейсом DiscountStrategy, не зная конкретики;
 *   - скидка не уводит цену ниже 0.
 *
 * ПОДСКАЗКА:
 *   Контекст делегирует: total(price) = strategy.apply(price). Это «движок» OCP.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте Checkout, посчитайте total с NoDiscount, смените на
        //       PercentDiscount(20), посчитайте снова
    }
}
