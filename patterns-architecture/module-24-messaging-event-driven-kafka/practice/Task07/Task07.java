/**
 * Задача 07 — Тема 24 (МИНИ-ПРОЕКТ OPS): события заказа, подписчики и DLQ
 *
 * Развитие OPS. Событие о заказе публикуется в топик; на него реагируют два
 * подписчика (склад и доставка); сбой обработки у подписчика уходит в DLQ.
 *
 * ЗАДАНИЕ:
 *   1. OrderPlaced (файл ...) — record (orderId, sku) (готов).
 *   2. OrderSubscriber (файл OrderSubscriber.java): void onOrder(OrderPlaced e).
 *   3. InventorySubscriber (файл ...): если sku == "OUT" → бросает RuntimeException
 *      (нет на складе); иначе печатает "резерв " + sku.
 *      ShippingSubscriber (файл ...): печатает "отгрузка " + orderId.
 *   4. OrderTopic (файл OrderTopic.java): subscribe(OrderSubscriber); publish(OrderPlaced)
 *      вызывает КАЖДОГО подписчика в своём try/catch — при исключении кладёт событие
 *      в DLQ и печатает "в DLQ: " + orderId; dlqSize().
 *   В main: подпишите склад и доставку; опубликуйте заказ с sku "BOOK" (всё ок) и
 *   заказ с sku "OUT" (склад падает → DLQ, доставка всё равно отрабатывает);
 *   выведите размер DLQ.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   резерв BOOK
 *   отгрузка A-1
 *   в DLQ: A-2
 *   отгрузка A-2
 *   В DLQ: 1
 *
 * ТРЕБОВАНИЯ:
 *   - событие получают ВСЕ подписчики (pub-sub);
 *   - сбой одного подписчика не мешает другому (изоляция через try/catch);
 *   - проблемное событие уходит в DLQ, а не теряется и не валит топик.
 *
 * ПОДСКАЗКА:
 *   Это сводит тему: pub-sub (Task02) + изоляция/DLQ (Task05). В Kafka роль топика
 *   и DLQ играл бы брокер; здесь — in-memory.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: OrderTopic; subscribe(Inventory), subscribe(Shipping);
        //       publish(new OrderPlaced("A-1","BOOK")); publish(new OrderPlaced("A-2","OUT"));
        //       вывести dlqSize()
    }
}
