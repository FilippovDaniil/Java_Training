/**
 * Задача 03 — Тема 20: CQRS — раздельные модели записи и чтения
 *
 * ЗАДАНИЕ:
 *   Разделите изменение и чтение на ДВЕ модели:
 *     - InventoryReadView (файл InventoryReadView.java) — read-модель: Map<sku, qty>;
 *       query qty(String sku); метод update(sku, qty) (вызывается write-стороной);
 *     - Inventory (файл Inventory.java) — write-модель: внутренний Map<sku, qty>;
 *       команды addStock(sku, qty) и removeStock(sku, qty) меняют состояние и
 *       синхронизируют read-модель через update(...).
 *   В main: addStock("BOOK", 10), removeStock("BOOK", 3); прочитайте остаток
 *   через read-модель.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Остаток BOOK (чтение): 7
 *
 * ТРЕБОВАНИЯ:
 *   - изменения идут только через write-модель (команды);
 *   - чтение — только через read-модель (запрос qty);
 *   - write-сторона обновляет read-сторону после изменения.
 *
 * ПОДСКАЗКА:
 *   CQRS можно применять и без Event Sourcing — здесь просто две модели.
 *   Read-модель оптимизирована под запросы, write — под изменения и правила.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: создайте Inventory с InventoryReadView; addStock/removeStock; прочитайте qty
    }
}
