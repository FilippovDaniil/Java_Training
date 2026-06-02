/**
 * Задача 02 — Модуль 68: Коллекция и элемент как разные ресурсы
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Спроектируйте два уровня ресурса задач:
 *     КОЛЛЕКЦИЯ  /api/tasks
 *       - GET  → "все задачи"
 *       - POST → "создать задачу в коллекции"
 *     ЭЛЕМЕНТ    /api/tasks/{id}
 *       - GET    → "задача <id>"
 *       - PUT    → "заменить задачу <id>"
 *       - DELETE → "удалить задачу <id>"
 *
 *   ВАЖНО: создание (POST) идёт на КОЛЛЕКЦИЮ, а не на элемент.
 *   Нельзя сделать POST /api/tasks/{id} для создания — это ошибка дизайна.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему создание — это POST на /api/tasks, а не PUT на /api/tasks/{id}?
 *
 * ПОДСКАЗКА: id присваивает сервер, клиент его заранее не знает (см. theory.md).
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@RestController
@RequestMapping("/api/tasks")
class CollectionController02 {

    // --- Коллекция /api/tasks ---

    // TODO: @GetMapping → "все задачи"
    public String all() {
        return null;
    }

    // TODO: @PostMapping → "создать задачу в коллекции"
    public String create() {
        return null;
    }

    // --- Элемент /api/tasks/{id} ---

    // TODO: @GetMapping("/{id}") → "задача " + id
    public String one(@PathVariable Long id) {
        return null;
    }

    // TODO: @PutMapping("/{id}") → "заменить задачу " + id
    public String replace(@PathVariable Long id) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → "удалить задачу " + id
    public String delete(@PathVariable Long id) {
        return null;
    }
}
