package m02_solid_srp_ocp.practice.task07;

/**
 * Задача 07 — Тема 02 (МИНИ-ПРОЕКТ OPS): расчёт стоимости заказа
 *
 * Развитие OPS из темы 01. Теперь у заказа есть позиции, выбираемый способ
 * доставки и политика скидки. Соберите расчёт по SRP + OCP.
 *
 * ЗАДАНИЕ:
 *   1. OrderLine (файл OrderLine.java) — позиция заказа: priceCents, weightGrams
 *      (данные, готовы).
 *   2. ShippingMethod (файл ShippingMethod.java): long costCents(int totalGrams);
 *      реализации StandardShipping и ExpressShipping (открыто для расширения).
 *   3. DiscountPolicy (файл DiscountPolicy.java): long apply(long amountCents);
 *      реализации NoDiscount и PercentDiscount.
 *   4. OrderPricingService (файл OrderPricingService.java) — ТОЛЬКО считает:
 *      метод long grandTotal(List<OrderLine> lines, ShippingMethod shipping,
 *      DiscountPolicy discount) = скидка(сумма позиций) + доставка(общий вес).
 *   В main соберите заказ из 2–3 позиций, посчитайте итог при разных доставке/скидке.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Сумма позиций: 30000
 *   Standard + без скидки: 30000 + доставка
 *   Express + -10%: ...
 *
 * ТРЕБОВАНИЯ:
 *   - OrderPricingService зависит только от абстракций ShippingMethod и
 *     DiscountPolicy (новые способы/скидки добавляются без его правок);
 *   - сервис только считает (расчёт и вывод разделены — вывод в main/рендерере);
 *   - суммирование цен и веса позиций — через перебор lines (композиция).
 *
 * ПОДСКАЗКА:
 *   grandTotal = discount.apply(сумма priceCents) + shipping.costCents(сумма weightGrams).
 *   Это «скелет» будущего OrderService, который в темах 16–17 получит ещё и оплату.
 */

import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        // TODO: создайте List<OrderLine>, посчитайте grandTotal с разными
        //       комбинациями ShippingMethod и DiscountPolicy
    }
}
