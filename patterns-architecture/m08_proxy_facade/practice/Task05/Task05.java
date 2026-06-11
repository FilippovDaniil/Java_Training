package m08_proxy_facade.practice.task05;

/**
 * Задача 05 — Тема 08: Facade для оформления заказа (OPS)
 *
 * ЗАДАНИЕ:
 *   Спрячьте оформление заказа (склад + оплата + доставка) за одним фасадом:
 *     - Inventory (файл Inventory.java): boolean reserve(String sku) → true/false;
 *     - Payment (файл Payment.java): boolean charge(long amountCents) → true/false;
 *     - Shipping (файл Shipping.java): String schedule(String sku) → номер отправления;
 *     - OrderFacade (файл OrderFacade.java): метод
 *       String placeOrder(String sku, long amountCents):
 *         1) reserve; если нет на складе — вернуть "нет на складе";
 *         2) charge; если оплата не прошла — вернуть "оплата отклонена";
 *         3) schedule и вернуть "заказ оформлен, отправление <id>".
 *   В main оформите успешный заказ через фасад.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   заказ оформлен, отправление SHIP-BOOK
 *
 * ТРЕБОВАНИЯ:
 *   - клиент вызывает только OrderFacade.placeOrder(...);
 *   - фасад координирует подсистемы и порядок шагов, обрабатывая отказы;
 *   - фасад не реализует логику склада/оплаты/доставки сам — только вызывает их.
 *
 * ПОДСКАЗКА:
 *   Это «репетиция» CheckoutFacade из Task07. Фасад скрывает от клиента, сколько
 *   шагов и сервисов задействовано при оформлении.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: создайте OrderFacade и оформите заказ через placeOrder(...)
    }
}
