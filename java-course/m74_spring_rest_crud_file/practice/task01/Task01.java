package m74_spring_rest_crud_file.practice.task01;

/**
 * Задача 01 — Модуль 74: Полный CRUD-скелет (повторение)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте полный CRUD для ресурса задач (хранилище в памяти Map<Long, Task01>):
 *     - POST   /api/tasks        @RequestParam title → 201 + созданная задача
 *     - GET    /api/tasks        → список
 *     - GET    /api/tasks/{id}   → 200 либо 404
 *     - PUT    /api/tasks/{id}   @RequestParam title → 200 (заменить целиком) либо 404
 *     - DELETE /api/tasks/{id}   → 204 либо 404
 *
 *   Подберите корректные статусы (201/200/404/204) через ResponseEntity.
 *
 * ЦЕЛЬ: закрепить «скелет» CRUD перед файловыми операциями.
 *
 * ПОДСКАЗКА: id-генератор AtomicLong; используйте наработки из модулей 67/69.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
