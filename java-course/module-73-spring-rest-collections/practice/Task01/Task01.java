/**
 * Задача 01 — Модуль 73: Возврат коллекции (и пустой = [], не null)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) all(): GET /api/tasks → вернуть список задач (3 штуки из seed).
 *   2) empty(): GET /api/tasks/empty → вернуть ПУСТОЙ список (List.of()), НЕ null.
 *      Проверьте: ответ должен быть [] со статусом 200 (а не null/404).
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему пустую выборку отдают как [] + 200, а не как null или 404?
 *
 * ПОДСКАЗКА: List.of() — неизменяемый пустой список; Jackson сериализует его как [].
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
