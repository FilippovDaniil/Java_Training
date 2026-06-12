package m14_grasp.practice.task02;

/**
 * Задача 02 — Тема 14: Creator (кто создаёт объект)
 *
 * ЗАДАНИЕ:
 *   Объект создаёт тот, кто его агрегирует. Пусть OrderLine создаётся ВНУТРИ
 *   Order (а не снаружи), потому что заказ владеет своими строками:
 *     - OrderLine (файл OrderLine.java): sku, qty, priceCents, subtotal();
 *     - Order (файл Order.java): метод addLine(String sku, int qty, long priceCents)
 *       сам создаёт new OrderLine(...) и кладёт в свой список; total(); size().
 *   В main добавляйте позиции через order.addLine(...) — клиент НЕ создаёт
 *   OrderLine сам.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Позиций: 2
 *   Итого: 32000
 *
 * ТРЕБОВАНИЯ:
 *   - OrderLine создаётся внутри Order (Creator), а не в main;
 *   - main передаёт только данные позиции, не готовый объект;
 *   - Order владеет жизненным циклом своих строк.
 *
 * ПОДСКАЗКА:
 *   Creator: B создаёт A, если B агрегирует/содержит A. Здесь Order содержит
 *   OrderLine → Order и создаёт их.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: добавьте позиции через order.addLine(sku, qty, price), выведите size() и total()
    }
}
