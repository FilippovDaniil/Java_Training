package m67_spring_rest_http_backend.practice.task04;

/**
 * Задача 04 — Модуль 67: Content negotiation (Accept → формат ответа)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте ДВА метода на ОДНОМ пути /api/task, различающиеся по produces:
 *     1) asJson(): produces = "application/json" — вернуть объект TaskInfo04
 *        (Jackson сериализует в JSON). Клиент с Accept: application/json получит JSON.
 *     2) asText(): produces = "text/plain" — вернуть строку
 *        "Задача: <title> [<status>]". Клиент с Accept: text/plain получит текст.
 *
 *   Проверьте вручную:
 *     curl -H "Accept: application/json" http://localhost:8080/api/task
 *     curl -H "Accept: text/plain"       http://localhost:8080/api/task
 *
 * ЦЕЛЬ: понять, как сервер выбирает представление по заголовку Accept.
 *
 * ПОДСКАЗКА:
 *   @GetMapping(value = "/api/task", produces = "application/json")
 *   @GetMapping(value = "/api/task", produces = "text/plain")
 *   Spring сам выберет нужный метод по Accept-заголовку запроса.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
