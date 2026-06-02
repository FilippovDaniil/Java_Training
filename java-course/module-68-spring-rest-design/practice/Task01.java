/**
 * Задача 01 — Модуль 68: Рефакторинг URI с глаголами в RESTful-стиль
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ДАНО (анти-паттерн, RPC-стиль): эндпоинты с глаголами в URI.
 *   GET  /getAllTasks
 *   GET  /getTaskById?id=42
 *   POST /createTask
 *   POST /deleteTask?id=42
 *
 * ЗАДАНИЕ:
 *   Перепишите контроллер в RESTful-стиль (ресурс + метод), сохранив поведение:
 *     - GET    /api/tasks          (вместо /getAllTasks)        → "список задач"
 *     - GET    /api/tasks/{id}     (вместо /getTaskById)        → "задача <id>"
 *     - POST   /api/tasks          (вместо /createTask)         → "создана"
 *     - DELETE /api/tasks/{id}     (вместо /deleteTask)         → "удалена <id>"
 *
 *   В комментарии к каждому методу напишите, какое правило дизайна вы применили.
 *
 * ЦЕЛЬ: перевести мышление с RPC ("вызови функцию") на REST ("действуй над ресурсом").
 *
 * ПОДСКАЗКА: см. таблицу правил URI в theory.md.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

// TODO: @RestController + @RequestMapping("/api/tasks")
class TaskRestController01 {

    // TODO: @GetMapping → "список задач"   (правило: ___)
    public String list() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → "задача " + id   (правило: ___)
    public String getOne(/* @PathVariable */ Long id) {
        return null;
    }

    // TODO: @PostMapping → "создана"   (правило: ___)
    public String create() {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → "удалена " + id   (правило: ___)
    public String delete(/* @PathVariable */ Long id) {
        return null;
    }
}
