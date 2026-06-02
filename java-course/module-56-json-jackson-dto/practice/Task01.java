/**
 * Задача 01 — Модуль 56: Сериализация объекта в JSON
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.fasterxml.jackson.core:jackson-databind:2.17.x (см. theory.md)
 *   Подключить в build.gradle:
 *     implementation 'com.fasterxml.jackson.core:jackson-databind:2.17.2'
 *
 * ЗАДАНИЕ:
 *   1) Создайте ObjectMapper.
 *   2) Создайте объект Product (готовый record ниже).
 *   3) Сериализуйте объект в JSON-строку с помощью writeValueAsString().
 *   4) Выведите JSON на экран.
 *   5) Дополнительно: выведите JSON с отступами (pretty print).
 *
 * ПОДСКАЗКА:
 *   ObjectMapper mapper = new ObjectMapper();
 *   String json = mapper.writeValueAsString(product);
 *   String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);
 */
import com.fasterxml.jackson.databind.ObjectMapper;

public class Task01 {

    public static void main(String[] args) throws Exception {
        // 1. Создайте ObjectMapper
        // TODO: ObjectMapper mapper = ...

        // 2. Создайте объект Product
        // TODO: Product product = new Product(...)

        // 3. Сериализуйте объект в JSON-строку
        // TODO: String json = ...

        // 4. Выведите результат
        // TODO: System.out.println(json);

        // 5. (Дополнительно) Выведите JSON с отступами
        // TODO: String pretty = ...
        //       System.out.println(pretty);
    }
}

// Готовый класс-модель (record Java 16+, Jackson 2.12+ поддерживает автоматически)
record Product(Long id, String name, double price) {}

/*
 * Ожидаемый вывод (компактный):
 *   {"id":1,"name":"Кофе","price":350.0}
 *
 * Ожидаемый вывод (pretty):
 *   {
 *     "id" : 1,
 *     "name" : "Кофе",
 *     "price" : 350.0
 *   }
 */
