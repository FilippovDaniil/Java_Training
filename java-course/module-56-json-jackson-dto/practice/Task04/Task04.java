/**
 * Задача 04 — Модуль 56: Вложенные объекты и коллекции
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.fasterxml.jackson.core:jackson-databind:2.17.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Структура заказа (Order) содержит:
 *     - покупателя (Customer) — вложенный объект;
 *     - список позиций (List<Item>) — вложенная коллекция.
 *
 *   1) Сериализуйте объект Order в JSON-строку (pretty print).
 *   2) Выведите JSON и убедитесь, что вложенные объекты отображаются корректно.
 *   3) Десериализуйте JSON обратно в Order.
 *   4) Выведите имя покупателя и суммарную стоимость позиций:
 *        items.stream().mapToDouble(i -> i.price() * i.quantity()).sum()
 *   5) Десериализуйте JSON-строку jsonOrders (список из двух заказов) в List<Order>
 *      с помощью TypeReference и выведите количество заказов.
 *
 * ПОДСКАЗКА:
 *   TypeReference нужен для параметризованных типов:
 *     List<Order> orders = mapper.readValue(json, new TypeReference<List<Order>>() {});
 *   Вложенные классы Jackson обрабатывает автоматически — никаких дополнительных
 *   аннотаций не требуется, если у классов есть геттеры/сеттеры или record.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class Task04 {

    // Тестовые данные — два заказа
    static final String JSON_ORDERS = """
            [
              {
                "id": 1,
                "customer": { "id": 10, "name": "Иван Петров" },
                "items": [
                  { "productName": "Кофе",  "price": 350.0, "quantity": 2 },
                  { "productName": "Круассан", "price": 120.0, "quantity": 3 }
                ]
              },
              {
                "id": 2,
                "customer": { "id": 11, "name": "Мария Сидорова" },
                "items": [
                  { "productName": "Чай", "price": 200.0, "quantity": 1 }
                ]
              }
            ]
            """;

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // 1. Создайте объект Order с покупателем и двумя позициями
        // TODO: Customer customer = new Customer(10L, "Иван Петров");
        // TODO: List<Item> items = List.of(
        //           new Item("Кофе", 350.0, 2),
        //           new Item("Круассан", 120.0, 3)
        //       );
        // TODO: Order order = new Order(1L, customer, items);

        // 2. Сериализуйте в JSON с pretty print
        // TODO: String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
        //       System.out.println(json);

        // 3. Десериализуйте обратно в Order
        // TODO: Order parsed = mapper.readValue(json, Order.class);

        // 4. Выведите имя покупателя и суммарную стоимость
        // TODO: System.out.println("Покупатель: " + parsed.customer().name());
        // TODO: double total = parsed.items().stream()
        //           .mapToDouble(i -> i.price() * i.quantity()).sum();
        //       System.out.println("Итого: " + total);

        // 5. Десериализуйте список заказов
        // TODO: List<Order> orders = mapper.readValue(JSON_ORDERS, new TypeReference<List<Order>>() {});
        //       System.out.println("Заказов: " + orders.size());
    }
}
