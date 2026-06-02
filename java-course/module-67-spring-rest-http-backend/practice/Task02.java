/**
 * Задача 02 — Модуль 67: Заголовки ответа (Location) и чтение заголовка запроса
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) create(): POST /api/tasks — сымитируйте создание задачи с id=42.
 *      Верните 201 Created и ОБЯЗАТЕЛЬНО заголовок Location: /api/tasks/42.
 *      Тело — строка "Задача создана".
 *      Используйте ResponseEntity.created(URI.create("/api/tasks/42")).body(...).
 *   2) whoAmI(): GET /api/whoami — прочитайте заголовок запроса Authorization
 *      через @RequestHeader и верните "Токен: <значение>".
 *      Если заголовка нет — он должен быть необязательным (required = false),
 *      тогда верните "Токен не передан".
 *
 * ЦЕЛЬ: научиться ставить заголовки ответа и читать заголовки запроса.
 *
 * ПОДСКАЗКА:
 *   @RequestHeader(value = "Authorization", required = false) String auth
 *   ResponseEntity.created(uri) — уже ставит статус 201 и заголовок Location.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@RestController
class HeaderController02 {

    @PostMapping("/api/tasks")
    public ResponseEntity<String> create() {
        // TODO: URI location = URI.create("/api/tasks/42");
        // TODO: return ResponseEntity.created(location).body("Задача создана");
        return null;
    }

    @GetMapping("/api/whoami")
    public String whoAmI(/* TODO: @RequestHeader(value="Authorization", required=false) */ String auth) {
        // TODO: если auth == null — верните "Токен не передан"
        // TODO: иначе — верните "Токен: " + auth
        return null;
    }
}
