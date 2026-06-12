package m69_spring_rest_controllers.practice.task02;

/**
 * Задача 02 — Модуль 69: Все источники аргументов в одном обработчике
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте ОДИН метод handle, который собирает данные из ЧЕТЫРЁХ источников:
 *     POST /api/tasks/{id}?notify=true
 *     Header: Authorization: Bearer xyz
 *     Body:  { "title": "Купить кофе" }
 *
 *   Аргументы:
 *     - @PathVariable Long id
 *     - @RequestParam(defaultValue = "false") boolean notify
 *     - @RequestHeader(value = "Authorization", required = false) String auth
 *     - @RequestBody CreateTaskDto body
 *
 *   Верните строку:
 *     "id=<id>, title=<body.title>, notify=<notify>, auth=<есть/нет>"
 *
 * ЦЕЛЬ: увидеть, что один метод одновременно читает путь, query, заголовок и тело.
 *
 * ПОДСКАЗКА: auth == null → "нет", иначе → "есть".
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
