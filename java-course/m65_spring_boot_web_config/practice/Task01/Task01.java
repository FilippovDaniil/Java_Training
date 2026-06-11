package m65_spring_boot_web_config.practice.task01;

/**
 * Задача 01 — Модуль 65: GET-эндпоинт, возвращающий DTO (JSON)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) ProductDto — это record с полями (Long id, String name, long priceKopecks). Готов.
 *   2) Пометьте CatalogController01 как @RestController с @RequestMapping("/api/products").
 *   3) Метод getOne(id) пометьте @GetMapping("/{id}"). Верните новый ProductDto
 *      с переданным id, именем "Демо-товар" и ценой 9990.
 *   4) Запустите и откройте http://localhost:8080/api/products/1
 *      Ответ должен быть JSON: {"id":1,"name":"Демо-товар","priceKopecks":9990}
 *
 * ЦЕЛЬ: убедиться, что Jackson сам сериализует record в JSON.
 *
 * ПОДСКАЗКА:
 *   @PathVariable Long id  — забирает {id} из URI.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
