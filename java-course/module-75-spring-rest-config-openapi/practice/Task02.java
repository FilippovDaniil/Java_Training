/**
 * Задача 02 — Модуль 75: Версионирование через заголовок
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Один и тот же путь /api/tasks/{id}, но разные методы выбираются по заголовку X-API-Version:
 *     - getV1(): @GetMapping(value = "/{id}", headers = "X-API-Version=1") → "v1: задача <id>"
 *     - getV2(): @GetMapping(value = "/{id}", headers = "X-API-Version=2") → "v2: задача <id> [NEW]"
 *
 *   Проверьте:
 *     curl -H "X-API-Version: 1" http://localhost:8080/api/tasks/5
 *     curl -H "X-API-Version: 2" http://localhost:8080/api/tasks/5
 *
 * ВОПРОС (ответьте комментарием):
 *   Плюс и минус версионирования через заголовок по сравнению с URI?
 *
 * ПОДСКАЗКА: атрибут headers в @GetMapping делает заголовок условием маппинга.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@RestController
@RequestMapping("/api/tasks")
class HeaderVersionController02 {

    // TODO: @GetMapping(value = "/{id}", headers = "X-API-Version=1")
    public String getV1(@PathVariable Long id) {
        // TODO: верните "v1: задача " + id
        return null;
    }

    // TODO: @GetMapping(value = "/{id}", headers = "X-API-Version=2")
    public String getV2(@PathVariable Long id) {
        // TODO: верните "v2: задача " + id + " [NEW]"
        return null;
    }
}
