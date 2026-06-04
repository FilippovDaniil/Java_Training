/**
 * Задача 06 — Модуль 104: @JsonTest + JacksonTester — сериализация/разбор DTO
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Проверьте маппинг DTO ↔ JSON В ИЗОЛЯЦИИ (без контроллера), через @JsonTest:
 *     1) Класс пометьте @JsonTest; @Autowired JacksonTester<TaskDto06> json.
 *     2) serializes(): assertThat(json.write(new TaskDto06(1L, "Кофе", "NEW")))
 *          .extractingJsonPathStringValue("$.title").isEqualTo("Кофе");
 *     3) deserializes(): TaskDto06 dto = json.parseObject(
 *          "{\"id\":1,\"title\":\"Кофе\",\"status\":\"NEW\"}");
 *          assertThat(dto.title()).isEqualTo("Кофе");
 *
 * ЦЕЛЬ: тестировать только JSON-слой (Jackson) — легче, чем @WebMvcTest.
 *
 * ВАЖНО: @JsonTest поднимает только Jackson; JacksonTester умеет write() и parseObject().
 *
 * ПОДСКАЗКА: assertThat(json.write(...)) — это AssertJ для JsonContent.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @JsonTest
class TaskDtoJsonTest06 {

    // TODO: @Autowired JacksonTester<TaskDto06> json;

    @Test
    void serializes() throws Exception {
        // TODO: assertThat(json.write(new TaskDto06(1L, "Кофе", "NEW")))
        //              .extractingJsonPathStringValue("$.title").isEqualTo("Кофе");
    }

    @Test
    void deserializes() throws Exception {
        // TODO: TaskDto06 dto = json.parseObject("{\"id\":1,\"title\":\"Кофе\",\"status\":\"NEW\"}");
        // TODO: assertThat(dto.title()).isEqualTo("Кофе");
    }
}
