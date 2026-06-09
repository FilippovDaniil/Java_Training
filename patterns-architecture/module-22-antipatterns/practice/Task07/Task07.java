/**
 * Задача 07 — Тема 22 (МИНИ-ПРОЕКТ OPS): рефакторинг анемичного заказа
 *
 * Развитие OPS. Был «больной» заказ: анемичный (логика в сервисе), цены — голый
 * long (Primitive Obsession), без инвариантов. Соберите ИСПРАВЛЕННУЮ версию,
 * объединив лечение нескольких антипаттернов.
 *
 * ЗАДАНИЕ:
 *   1. Money (файл Money.java): VO; plus(Money), times(int); equals/toString
 *      (лечит Primitive Obsession).
 *   2. OrderLine (файл OrderLine.java): sku, qty, Money unitPrice; subtotal() =
 *      unitPrice.times(qty) (поведение у данных — лечит Anemic Model).
 *   3. Order (файл Order.java) — богатый агрегат: addLine(sku, qty, Money)
 *      (запрещён после confirm()), confirm(), total() → Money, lines() — копия.
 *   В main: соберите заказ из 2 позиций в Money, выведите total(); подтвердите;
 *   попробуйте добавить позицию после подтверждения (исключение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Итого: 32000 RUB
 *   Добавление после confirm: запрещено
 *
 * ТРЕБОВАНИЯ:
 *   - деньги — Money (не long): Primitive Obsession вылечен;
 *   - расчёт и инварианты — в Order/OrderLine (не во внешнем сервисе): Anemic Model вылечен;
 *   - нельзя менять подтверждённый заказ (инвариант агрегата).
 *
 * ПОДСКАЗКА:
 *   Это сводит тему воедино: богатая модель (Information Expert), Value Object,
 *   агрегат с инвариантами — против анемичности и одержимости примитивами.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите Order с позициями в Money; total(); confirm(); addLine после confirm (поймать)
    }
}
