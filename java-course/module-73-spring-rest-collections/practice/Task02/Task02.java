/**
 * Задача 02 — Модуль 73: Ручная пагинация (page/size + subList)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   page(): GET /api/tasks?page=0&size=2
 *     - @RequestParam(defaultValue = "0") int page
 *     - @RequestParam(defaultValue = "20") int size
 *     Верните срез списка ALL по странице. Аккуратно с границами:
 *       from = page * size;
 *       to   = Math.min(from + size, ALL.size());
 *       если from >= ALL.size() → вернуть List.of();
 *       иначе ALL.subList(from, to).
 *
 *   Проверьте: ?page=0&size=2 → первые 2; ?page=1&size=2 → следующие 2;
 *              ?page=99&size=2 → [] (без исключения!).
 *
 * ЦЕЛЬ: безопасно нарезать список на страницы.
 *
 * ПОДСКАЗКА: главный риск — IndexOutOfBoundsException; защищайтесь Math.min и проверкой from.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.IntStream;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
