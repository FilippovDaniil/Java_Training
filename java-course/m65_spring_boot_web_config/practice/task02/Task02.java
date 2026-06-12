package m65_spring_boot_web_config.practice.task02;

/**
 * Задача 02 — Модуль 65: POST + @RequestBody + статус 201 Created
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Пометьте OrderController02 как @RestController с @RequestMapping("/api/orders").
 *   2) Метод create(dto) пометьте @PostMapping и принимайте тело через @RequestBody.
 *   3) Сымитируйте сохранение: верните ResponseEntity со статусом 201 Created
 *      и телом — тем же заказом, но с присвоенным id (например, 100).
 *
 * ПРИМЕР ЗАПРОСА:
 *   POST /api/orders
 *   Content-Type: application/json
 *   { "product": "Ноутбук", "quantity": 2 }
 *
 * ОЖИДАЕМЫЙ ОТВЕТ: HTTP 201, тело {"id":100,"product":"Ноутбук","quantity":2}
 *
 * ПОДСКАЗКА:
 *   ResponseEntity.status(HttpStatus.CREATED).body(...)
 *   record можно «скопировать с изменением» через создание нового экземпляра.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
