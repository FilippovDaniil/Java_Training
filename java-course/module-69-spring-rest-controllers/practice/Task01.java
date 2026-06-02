/**
 * Задача 01 — Модуль 69: @RestController и маппинг путей
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Пометьте класс @RestController и общим префиксом @RequestMapping("/api/tasks").
 *   2) Реализуйте три маппинга:
 *        - GET /api/tasks            → "все задачи"
 *        - GET /api/tasks/{id}       → "задача " + id
 *        - GET /api/tasks/{id}/comments/{cid} → "комментарий " + cid + " задачи " + id
 *   3) Запустите и проверьте все три URL в браузере.
 *
 * ЦЕЛЬ: освоить шаблоны путей с одной и несколькими переменными.
 *
 * ПОДСКАЗКА:
 *   @GetMapping("/{id}/comments/{cid}") — две переменные пути в одном шаблоне.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

// TODO: @RestController + @RequestMapping("/api/tasks")
class MappingController01 {

    // TODO: @GetMapping → "все задачи"
    public String all() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → "задача " + id
    public String one(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping("/{id}/comments/{cid}")
    public String comment(@PathVariable Long id, @PathVariable Long cid) {
        // TODO: верните "комментарий " + cid + " задачи " + id
        return null;
    }
}
