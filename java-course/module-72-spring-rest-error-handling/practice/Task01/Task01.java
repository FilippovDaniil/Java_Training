/**
 * Задача 01 — Модуль 72: ResponseStatusException
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   В методе get(id): если id <= 0 — бросьте ResponseStatusException
 *   со статусом 404 NOT_FOUND и сообщением "Задача " + id + " не найдена".
 *   Иначе верните "Задача " + id.
 *
 *   Проверьте:
 *     GET /api/tasks/5  → 200 "Задача 5"
 *     GET /api/tasks/0  → 404 (в теле — стандартный ProblemDetail с detail)
 *
 * ЦЕЛЬ: самый быстрый способ вернуть HTTP-ошибку прямо из кода.
 *
 * ПОДСКАЗКА:
 *   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "...");
 *   В Spring Boot 3 это уже даёт тело в формате ProblemDetail.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
