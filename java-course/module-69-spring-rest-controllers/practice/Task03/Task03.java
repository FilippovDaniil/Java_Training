/**
 * Задача 03 — Модуль 69: consumes / produces и фильтр по параметру
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) createJson(): @PostMapping(consumes = "application/json", produces = "application/json").
 *      Принимает @RequestBody CreateTaskDto, возвращает ту же задачу как объект (JSON).
 *      Запрос без Content-Type: application/json должен отклоняться (415).
 *   2) Два GET на ОДНОМ пути /api/tasks, различающиеся по наличию параметра:
 *        - listAll():   @GetMapping                       → "все задачи"
 *        - listByStatus(): @GetMapping(params = "status")  → "задачи со статусом " + status
 *      Spring выберет нужный метод: есть ?status=... → второй, иначе → первый.
 *
 * ПРОВЕРКА:
 *   GET /api/tasks            → "все задачи"
 *   GET /api/tasks?status=DONE → "задачи со статусом DONE"
 *
 * ЦЕЛЬ: понять, как consumes/produces и params уточняют выбор обработчика.
 *
 * ПОДСКАЗКА: при params="status" параметр становится обязательным условием маппинга.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
