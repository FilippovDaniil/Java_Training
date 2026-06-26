package m56_json_jackson_dto.practice.task02;

/**
 * Задача 02 — Модуль 56: Десериализация JSON в объект
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.fasterxml.jackson.core:jackson-databind:2.17.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Дан JSON-литерал в строковой переменной.
 *   1) Создайте ObjectMapper.
 *   2) Распарсите JSON в объект Product с помощью readValue().
 *   3) Выведите поля объекта (id, name, price) на экран.
 *   4) Распарсите JSON-массив в List<Product> с помощью TypeReference.
 *   5) Выведите количество товаров и имя каждого.
 *
 * ПОДСКАЗКА:
 *   Product p = mapper.readValue(jsonSingle, Product.class);
 *   List<Product> list = mapper.readValue(jsonArray,
 *           new com.fasterxml.jackson.core.type.TypeReference<List<Product>>() {});
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class Task02 {

    // Входные данные
    static final String JSON_SINGLE = """
            {"id": 2, "name": "Чай", "price": 200.0}
            """;

    static final String JSON_ARRAY = """
            [
              {"id": 1, "name": "Кофе",  "price": 350.0},
              {"id": 2, "name": "Чай",   "price": 200.0},
              {"id": 3, "name": "Какао", "price": 280.0}
            ]
            """;

    public static void main(String[] args) throws Exception {
        // 1. Создайте ObjectMapper
        // TODO: ObjectMapper mapper = ...
        ObjectMapper mapper = new ObjectMapper();

        // 2. Десериализуйте JSON_SINGLE в Product
        // TODO: Product product = ...
        Product product = mapper.readValue(JSON_SINGLE, Product.class);

        // 3. Выведите поля объекта
        // TODO: System.out.println("id="   + product.id());
        //       System.out.println("name=" + product.name());
        //       System.out.println("price="+ product.price());
        System.out.println("id="   + product.id());
        System.out.println("name=" + product.name());
        System.out.println("price="+ product.price());

        // 4. Десериализуйте JSON_ARRAY в List<Product>
        // TODO: List<Product> products = ...
        ArrayList<Product> products = mapper.readValue(JSON_ARRAY, new TypeReference<ArrayList<Product>>() {});

        // 5. Выведите количество и имена
        // TODO: System.out.println("Товаров: " + products.size());
        //       products.forEach(p -> System.out.println(" - " + p.name()));
        System.out.println("Товаров: " + products.size());
        products.forEach(p -> System.out.println(" - " + p.name() + ": " + p.price()));
    }
}
