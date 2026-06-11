package m22_antipatterns.practice.task01;

/**
 * Задача 01 — Тема 22: лечим Anemic Domain Model
 *
 * ЗАДАНИЕ:
 *   Раньше Order был анемичным (только данные), а сумму считал внешний сервис,
 *   лазая в его геттеры. Сделайте модель БОГАТОЙ — перенесите поведение внутрь:
 *     - OrderLine (файл OrderLine.java): priceCents, qty; метод subtotal() = qty*price
 *       (строка сама знает свою сумму);
 *     - Order (файл Order.java): хранит List<OrderLine>, addLine(...), и САМ
 *       считает total() (не внешний сервис).
 *   В main соберите заказ и выведите total(), вызвав его НА самом заказе.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Итого: 32000
 *
 * ТРЕБОВАНИЯ:
 *   - сумму считает Order/OrderLine (поведение там, где данные), а не внешний сервис;
 *   - в main нет «ручного» перебора чужих полей для расчёта;
 *   - модель перестаёт быть «мешком геттеров».
 *
 * ПОДСКАЗКА:
 *   Это применение Information Expert (тема 14) против Anemic Domain Model.
 *   Антипаттерн ушёл — логика вернулась к данным.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: соберите Order из OrderLine, выведите order.total()
    }
}
