/**
 * Задача 01 — Модуль 67: Правильные коды статуса для разных ситуаций
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте методы StatusController01 так, чтобы каждый возвращал КОРРЕКТНЫЙ статус
 *   через ResponseEntity:
 *     - read()   GET  /api/demo/read    → 200 OK с телом "данные"
 *     - created() POST /api/demo/create → 201 Created с телом "создано"
 *     - noBody() DELETE /api/demo/clear → 204 No Content (без тела)
 *     - missing() GET /api/demo/missing → 404 Not Found
 *
 * ЦЕЛЬ: закрепить соответствие "ситуация → код статуса" (см. таблицу в theory.md).
 *
 * ПОДСКАЗКА:
 *   ResponseEntity.ok("данные")
 *   ResponseEntity.status(HttpStatus.CREATED).body("создано")
 *   ResponseEntity.noContent().build()
 *   ResponseEntity.notFound().build()
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
