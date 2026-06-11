package m48_database_design.practice;

/**
 * Задача 04 — Модуль 48: Приведение ко 2НФ
 *
 * ДАНО (в 1НФ, но не во 2НФ):
 *   order_items(order_id, product_id, product_name, product_price, quantity)
 *   Составной ключ: (order_id, product_id).
 *   Проблема: product_name и product_price зависят только от product_id
 *   (части ключа) → частичная зависимость.
 *
 * ЗАДАНИЕ:
 *   1) объясните в комментарии частичную зависимость;
 *   2) приведите ко 2НФ: вынесите атрибуты товара в отдельную таблицу
 *      products(product_id, product_name, product_price);
 *   3) напишите CREATE TABLE для результата.
 *
 * ПОДСКАЗКА:
 *   После нормализации order_items хранит только (order_id, product_id,
 *   quantity), а данные о товаре — в products.
 */
public class Task04 {
    public static void main(String[] args) {
        String sql = """
            -- Приведите order_items ко 2НФ (устраните частичную зависимость)
            """;
        System.out.println(sql);
    }
}
