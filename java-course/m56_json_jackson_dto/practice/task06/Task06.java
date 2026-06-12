package m56_json_jackson_dto.practice.task06;

/**
 * Задача 06 — Модуль 56: Неизвестные поля и @JsonInclude
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.fasterxml.jackson.core:jackson-databind:2.17.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Часть A — FAIL_ON_UNKNOWN_PROPERTIES:
 *     1) Попробуйте десериализовать JSON с «лишними» полями (rating, brand)
 *        в класс ProductDto — по умолчанию Jackson бросает UnrecognizedPropertyException.
 *        Поймайте и выведите исключение.
 *     2) Настройте mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
 *        и повторите — теперь должно работать без ошибок.
 *     3) Альтернатива: добавьте @JsonIgnoreProperties(ignoreUnknown = true) на класс ProductDto
 *        и десериализуйте тем же «строгим» mapper — тоже должно работать.
 *
 *   Часть B — @JsonInclude:
 *     4) Создайте ProductDto со всеми null-полями (только id задан).
 *        Без аннотации @JsonInclude в JSON будут "description":null, "imageUrl":null.
 *     5) Добавьте @JsonInclude(JsonInclude.Include.NON_NULL) на ProductDto и повторите —
 *        null-поля должны исчезнуть из JSON.
 *     6) Сравните два JSON (с аннотацией и без) и выведите оба.
 *
 * ПОДСКАЗКА:
 *   import com.fasterxml.jackson.databind.DeserializationFeature;
 *   import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 *   import com.fasterxml.jackson.annotation.JsonInclude;
 *
 *   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Task06 {

    // JSON с «лишними» полями, которых нет в ProductDto
    static final String JSON_WITH_EXTRA = """
            {
              "id": 5,
              "name": "Ноутбук",
              "price": 79999.0,
              "rating": 4.8,
              "brand": "TechCorp"
            }
            """;

    public static void main(String[] args) throws Exception {
        ObjectMapper strictMapper = new ObjectMapper();
        ObjectMapper lenientMapper = new ObjectMapper();

        // ЧАСТЬ A — неизвестные поля

        // 1. Попробуйте десериализовать «строгим» mapper (должно выброситься исключение)
        // TODO: try {
        //           ProductDto dto = strictMapper.readValue(JSON_WITH_EXTRA, ProductDto.class);
        //       } catch (Exception e) {
        //           System.out.println("Ожидаемая ошибка: " + e.getMessage());
        //       }

        // 2. Настройте lenientMapper и десериализуйте снова
        // TODO: lenientMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //       ProductDto dto = lenientMapper.readValue(JSON_WITH_EXTRA, ProductDto.class);
        //       System.out.println("Успешно: " + dto.getName());

        // 3. @JsonIgnoreProperties(ignoreUnknown = true) — раскомментируйте аннотацию
        //    на классе ProductDto и десериализуйте строгим mapper
        // TODO: ProductDto dto2 = strictMapper.readValue(JSON_WITH_EXTRA, ProductDto.class);
        //       System.out.println("Через @JsonIgnoreProperties: " + dto2.getName());

        // ЧАСТЬ B — @JsonInclude

        // 4. Создайте ProductDto с null-полями
        // TODO: ProductDto partial = new ProductDto();
        //       partial.setId(5L);
        //       partial.setName("Ноутбук");
        //       // description и imageUrl — не задаём (null)

        // 5. Сериализуйте без @JsonInclude (раскомментируйте и убедитесь что null присутствуют)
        // TODO: String withNulls = lenientMapper.writeValueAsString(partial);
        //       System.out.println("С null-полями: " + withNulls);

        // 6. Добавьте @JsonInclude(JsonInclude.Include.NON_NULL) на класс ProductDto
        //    и сериализуйте снова
        // TODO: String withoutNulls = lenientMapper.writeValueAsString(partial);
        //       System.out.println("Без null-полей: " + withoutNulls);
    }
}
