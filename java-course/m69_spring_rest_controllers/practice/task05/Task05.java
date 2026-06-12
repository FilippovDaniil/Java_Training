package m69_spring_rest_controllers.practice.task05;

/**
 * Задача 05 — Модуль 69: @RequestParam — список, значение по умолчанию, optional
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) byTags(): GET /api/tasks/by-tags?tag=java&tag=spring&tag=rest
 *      Принять @RequestParam List<String> tags и вернуть "Тегов: <n> → [java, spring, rest]".
 *      (один и тот же параметр, повторённый несколько раз, связывается в List)
 *   2) withDefaults(): GET /api/tasks/filter
 *      - @RequestParam(defaultValue = "ALL") String status
 *      - @RequestParam(defaultValue = "10")  int limit
 *      Вернуть "status=<status>, limit=<limit>" (без параметров → "status=ALL, limit=10").
 *   3) optional(): GET /api/tasks/maybe
 *      - @RequestParam Optional<String> q
 *      Вернуть "q задан: <значение>" или "q не задан".
 *
 * ЦЕЛЬ: освоить продвинутые формы @RequestParam (коллекция, default, Optional).
 *
 * ПОДСКАЗКА:
 *   ?tag=a&tag=b → List.of("a","b"); Optional<String> q.isPresent().
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
