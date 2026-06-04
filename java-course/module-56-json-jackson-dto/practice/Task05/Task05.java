/**
 * Задача 05 — Модуль 56: Даты и @JsonFormat (JavaTimeModule)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.fasterxml.jackson.core:jackson-databind:2.17.x
 *   com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.x  ← обязательно для java.time
 *
 *   В build.gradle добавить:
 *     implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.2'
 *
 * ЗАДАНИЕ:
 *   1) Создайте ObjectMapper, зарегистрируйте JavaTimeModule,
 *      отключите WRITE_DATES_AS_TIMESTAMPS.
 *   2) Создайте объект BlogPost (ниже) с текущей датой (LocalDate.now())
 *      и текущим временем (LocalDateTime.now()).
 *   3) Сериализуйте в JSON — убедитесь, что publishedOn выводится как "2024-03-15",
 *      а createdAt — как "2024-03-15T10:30:00".
 *   4) Десериализуйте JSON обратно в BlogPost и выведите год публикации.
 *   5) Попробуйте сериализовать без JavaTimeModule — поймайте и выведите исключение
 *      (InvalidDefinitionException / InvalidTypeIdException).
 *
 * ПОДСКАЗКА:
 *   import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
 *   import com.fasterxml.jackson.databind.SerializationFeature;
 *
 *   mapper.registerModule(new JavaTimeModule());
 *   mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
 *
 *   @JsonFormat(pattern = "yyyy-MM-dd") управляет форматом отдельного поля.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task05 {

    public static void main(String[] args) throws Exception {
        // 1. Создайте ObjectMapper с JavaTimeModule
        // TODO: ObjectMapper mapper = new ObjectMapper();
        //       mapper.registerModule(new JavaTimeModule());
        //       mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 2. Создайте объект BlogPost
        // TODO: BlogPost post = new BlogPost(
        //           1L, "Привет, Jackson!", LocalDate.now(), LocalDateTime.now()
        //       );

        // 3. Сериализуйте и выведите JSON
        // TODO: String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(post);
        //       System.out.println(json);

        // 4. Десериализуйте обратно и выведите год
        // TODO: BlogPost parsed = mapper.readValue(json, BlogPost.class);
        //       System.out.println("Год публикации: " + parsed.publishedOn().getYear());

        // 5. Попробуйте без JavaTimeModule (ожидается исключение)
        // TODO: try {
        //           ObjectMapper badMapper = new ObjectMapper();
        //           badMapper.writeValueAsString(post);
        //       } catch (Exception e) {
        //           System.out.println("Ожидаемая ошибка: " + e.getMessage());
        //       }
    }
}
