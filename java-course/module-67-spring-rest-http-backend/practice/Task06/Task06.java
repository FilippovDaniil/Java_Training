/**
 * Задача 06 — Модуль 67: Дерево решений по коду статуса (4xx)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Метод process(): POST /api/tasks/validate?title=...&token=...&id=...
 *   Верните статус по правилам (проверяйте СВЕРХУ ВНИЗ — порядок важен!):
 *     1) token отсутствует/пустой               → 401 Unauthorized
 *     2) token не равен "admin"                  → 403 Forbidden
 *     3) title пустой                            → 400 Bad Request  ("title обязателен")
 *     4) id равен 999 (как будто уже существует) → 409 Conflict     ("дубликат")
 *     5) иначе                                   → 201 Created      ("создано")
 *
 *   Проверьте разные комбинации параметров через curl/Postman.
 *
 * ЦЕЛЬ: натренировать выбор кода ошибки по дереву решений (см. ASCII-схему в theory.md).
 *
 * ПОДСКАЗКА:
 *   Все параметры — @RequestParam(required = false).
 *   ResponseEntity.status(HttpStatus.FORBIDDEN).body("...")
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
