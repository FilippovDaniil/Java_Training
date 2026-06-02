/**
 * Задача 01 — Модуль 70: Раздельные Request и Response DTO
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Объявите два DTO как record:
 *        - CreateTaskRequest(String title, String assignee)  — приходит от клиента
 *        - TaskResponse(Long id, String title, String assignee, String status) — уходит клиенту
 *      Обратите внимание: в запросе НЕТ id и status (их задаёт сервер).
 *   2) В контроллере метод create: @PostMapping, @RequestBody CreateTaskRequest.
 *      Соберите TaskResponse с id=1, статусом "NEW" и данными из запроса. Верните его.
 *
 * ПРИМЕР:
 *   POST /api/tasks   { "title":"Купить кофе", "assignee":"ivan" }
 *   Ответ:            { "id":1, "title":"Купить кофе", "assignee":"ivan", "status":"NEW" }
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему форма запроса и форма ответа РАЗНЫЕ?
 *
 * ПОДСКАЗКА: record-DTO Jackson сериализует/десериализует из коробки.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

// TODO: record CreateTaskRequest(String title, String assignee)
// TODO: record TaskResponse(Long id, String title, String assignee, String status)

@RestController
@RequestMapping("/api/tasks")
class DtoController01 {

    // TODO: @PostMapping
    public Object create(/* @RequestBody CreateTaskRequest req */ Object req) {
        // TODO: верните new TaskResponse(1L, req.title(), req.assignee(), "NEW")
        return null;
    }
}
