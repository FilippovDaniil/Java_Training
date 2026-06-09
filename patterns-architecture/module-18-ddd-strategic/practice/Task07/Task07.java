/**
 * Задача 07 — Тема 18 (МИНИ-ПРОЕКТ OPS): агрегат Order + Value Object Money
 *
 * Развитие OPS. Соберите доменное ядро заказа из строительных блоков DDD:
 *   - VALUE OBJECT Money — неизменяемые деньги (равенство по значению);
 *   - AGGREGATE Order — корень, стерегущий инварианты; OrderLine внутри.
 *
 * ЗАДАНИЕ:
 *   1. Money (файл Money.java): amountCents, currency; plus(Money), times(int qty)
 *      (возвращают новый Money); equals/hashCode по значению; toString "X RUB".
 *   2. OrderLine (файл OrderLine.java): sku, qty, Money unitPrice;
 *      subtotal() = unitPrice.times(qty).
 *   3. Order (файл Order.java) — Aggregate Root: addLine(sku, qty, Money)
 *      (запрещён после confirm()); confirm(); total() возвращает Money (сумма
 *      subtotal, начиная с нуля в нужной валюте); lines() — копия.
 *   В main: соберите заказ из 2 позиций в Money, выведите итог; подтвердите;
 *   попробуйте добавить позицию после подтверждения (исключение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Итого: 32000 RUB
 *   Добавление после confirm: запрещено
 *
 * ТРЕБОВАНИЯ:
 *   - цена — Value Object Money (не «голый» long), операции возвращают новый Money;
 *   - Order — корень: инварианты (нельзя менять подтверждённый) и total() в нём;
 *   - наружу — копия строк; внутренности не меняются в обход корня.
 *
 * ПОДСКАЗКА:
 *   total() начинайте с new Money(0, "RUB") и складывайте subtotal() строк через
 *   plus(). Это доменное ядро, которое в теме 19 получит репозиторий и события.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите Order с позициями в Money; total(); confirm(); addLine после confirm (поймать)
    }
}
