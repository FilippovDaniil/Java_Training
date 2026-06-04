/**
 * Задача 05 — Модуль 70: Дженерик-обёртка ответа ApiResponse<T>
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Объявите дженерик-обёртку:
 *        record ApiResponse<T>(T data, String message) {
 *            static <T> ApiResponse<T> ok(T data) { return new ApiResponse<>(data, "OK"); }
 *        }
 *   2) В контроллере:
 *        - getTask(): @GetMapping("/{id}") → ApiResponse<TaskDto05> (обёрнутый объект)
 *        - getList(): @GetMapping        → ApiResponse<List<TaskDto05>> (обёрнутый список)
 *      Используйте фабрику ApiResponse.ok(...).
 *
 * ОЖИДАЕМЫЙ JSON для getTask:
 *   { "data": { "id":1, "title":"X" }, "message":"OK" }
 *
 * ЦЕЛЬ: единый формат ответа с метаданными через дженерики; убедиться, что
 *       Jackson корректно сериализует ApiResponse<T> для любого T.
 *
 * ПОДСКАЗКА: стирание типов не мешает СЕРИАЛИЗАЦИИ — Jackson смотрит на реальный объект.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
