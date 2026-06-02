/**
 * Задача 05 — Модуль 68: Фильтрация, сортировка и пагинация через query-параметры
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Спроектируйте «правильную» коллекцию с параметрами (НЕ через путь!):
 *     GET /api/tasks?status=DONE&assignee=ivan&sort=createdAt&page=0&size=20
 *
 *   Метод search принимает:
 *     - status   @RequestParam(required = false)
 *     - assignee @RequestParam(required = false)
 *     - sort     @RequestParam(defaultValue = "id")
 *     - page     @RequestParam(defaultValue = "0")
 *     - size     @RequestParam(defaultValue = "20")
 *   Верните строку-описание, например:
 *     "Фильтр: status=DONE, assignee=ivan; сортировка по createdAt; страница 0 по 20"
 *   (если фильтр не задан — пишите "любой").
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему /api/tasks/status/DONE — плохой дизайн, а /api/tasks?status=DONE — хороший?
 *
 * ПОДСКАЗКА: фильтр — это НЕ новый ресурс, а уточнение запроса к коллекции.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@RestController
@RequestMapping("/api/tasks")
class FilterController05 {

    @GetMapping
    public String search(
            /* TODO: @RequestParam(required = false) */ String status,
            /* TODO: @RequestParam(required = false) */ String assignee,
            /* TODO: @RequestParam(defaultValue = "id") */ String sort,
            /* TODO: @RequestParam(defaultValue = "0") */ int page,
            /* TODO: @RequestParam(defaultValue = "20") */ int size) {
        // TODO: соберите строку-описание (status/assignee == null → "любой")
        return null;
    }
}
