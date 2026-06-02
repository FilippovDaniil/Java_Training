/**
 * Задача 06 — Модуль 68: HATEOAS — гиперссылки в ответе (уровень 3, ручная версия)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Сделайте ответ "самоописываемым": вместе с данными задачи верните ссылки
 *   на возможные действия (это идея HATEOAS, уровень 3 Richardson Maturity Model).
 *
 *   1) Соберите ответ как Map<String, Object> с полями:
 *        "id"     → id
 *        "title"  → "Купить кофе"
 *        "status" → "NEW"
 *        "_links" → Map с ключами:
 *                     "self"     → "/api/tasks/" + id
 *                     "complete" → "/api/tasks/" + id   (метод PATCH со status=DONE)
 *                     "delete"   → "/api/tasks/" + id
 *   2) Метод getOne: @GetMapping("/{id}") → вернуть эту Map (Jackson сделает JSON).
 *
 * ОЖИДАЕМЫЙ JSON (примерно):
 *   { "id":42, "title":"Купить кофе", "status":"NEW",
 *     "_links": { "self":"/api/tasks/42", "complete":"/api/tasks/42", "delete":"/api/tasks/42" } }
 *
 * ВОПРОС (ответьте комментарием):
 *   Какое преимущество даёт клиенту наличие _links вместо «зашитых» в код URI?
 *
 * ПОДСКАЗКА:
 *   Map.of(...) для неизменяемой карты; вкладывайте Map в Map для "_links".
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

@RestController
@RequestMapping("/api/tasks")
class HateoasController06 {

    @GetMapping("/{id}")
    public Map<String, Object> getOne(@PathVariable Long id) {
        // TODO: соберите Map с id, title, status и вложенной "_links"
        return null;
    }
}
