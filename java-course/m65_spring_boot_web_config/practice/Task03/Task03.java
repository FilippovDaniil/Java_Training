package m65_spring_boot_web_config.practice.task03;

/**
 * Задача 03 — Модуль 65: @PathVariable + @RequestParam (фильтрация и пагинация)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Пометьте SearchController03 как @RestController с @RequestMapping("/api/products").
 *   2) Метод search:
 *        - @GetMapping("/search")
 *        - @RequestParam String category               — обязательный
 *        - @RequestParam(defaultValue = "0")   int page — по умолчанию 0
 *        - @RequestParam(defaultValue = "20")  int size — по умолчанию 20
 *        - @RequestParam(required = false) String name  — необязательный фильтр
 *      Верните строку-описание запроса, например:
 *        "Поиск: категория=Электроника, страница=0, размер=20, фильтр по имени=нет"
 *   3) Метод byCategoryAndId:
 *        - @GetMapping("/{category}/{id}")
 *        - верните "Товар <id> в категории <category>"
 *
 * ПРИМЕРЫ:
 *   GET /api/products/search?category=Электроника
 *   GET /api/products/search?category=Книги&page=2&size=10&name=Java
 *   GET /api/products/Электроника/42
 *
 * ПОДСКАЗКА:
 *   required = false → параметр может отсутствовать (будет null);
 *   defaultValue     → подставится, если параметр не передан.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
