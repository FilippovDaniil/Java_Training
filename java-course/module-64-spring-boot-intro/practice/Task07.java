/**
 * Задача 07 — Модуль 64: МИНИ-ПРОЕКТ «Минимальный сервис магазина»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать вместе всё из модуля 64 — @SpringBootApplication, @Service,
 *       CommandLineRunner (сидинг), @RestController (эндпоинты).
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) ProductService07 (@Service) — хранилище товаров в памяти (List<String>):
 *        - add(String name)        — добавить товар
 *        - findAll()               — вернуть List<String> всех товаров
 *        - count()                 — вернуть количество товаров
 *
 *   2) SeedRunner07 (@Component, CommandLineRunner) — при старте засеять три товара:
 *        "Ноутбук", "Мышь", "Клавиатура" и вывести "Засеяно товаров: <count>".
 *
 *   3) ProductController07 (@RestController):
 *        - GET /products        → вернуть список всех товаров (List<String> → JSON-массив)
 *        - GET /products/count  → вернуть количество товаров
 *        - POST /products?name=X → добавить товар (через @RequestParam) и вернуть его
 *
 *   4) Запустите приложение. Проверьте:
 *        http://localhost:8080/products
 *        http://localhost:8080/products/count
 *        POST http://localhost:8080/products?name=Монитор  (например, через curl/Postman)
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP] ──► ProductController07 ──► ProductService07 (List<String> в памяти)
 *                                          ▲
 *                                  SeedRunner07 (сидинг при старте)
 *
 * ПОДСКАЗКА:
 *   @RestController возвращает List<String> — Jackson сам сериализует в JSON.
 *   @RequestParam String name — читает ?name=... из строки запроса.
 *   Внедряйте ProductService07 через конструктор и в Runner, и в Controller.
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// ============================================================
// Сервис товаров (каркас)
// ============================================================

// TODO: добавьте @Service
class ProductService07 {
    private final List<String> products = new ArrayList<>();

    public void add(String name) {
        // TODO: добавьте name в products
    }

    public List<String> findAll() {
        // TODO: верните products
        return null;
    }

    public int count() {
        // TODO: верните размер списка
        return 0;
    }
}

// ============================================================
// Сидинг при старте (каркас)
// ============================================================

// TODO: добавьте @Component
class SeedRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductService07 через конструктор

    @Override
    public void run(String... args) {
        // TODO: добавьте "Ноутбук", "Мышь", "Клавиатура"
        // TODO: выведите "Засеяно товаров: " + service.count()
    }
}

// ============================================================
// REST-контроллер (каркас)
// ============================================================

// TODO: добавьте @RestController
class ProductController07 {

    // TODO: внедрите ProductService07 через конструктор

    // TODO: @GetMapping("/products")
    public List<String> all() {
        return null;
    }

    // TODO: @GetMapping("/products/count")
    public int count() {
        return 0;
    }

    // TODO: @PostMapping("/products")
    public String add(/* TODO: @RequestParam */ String name) {
        // TODO: добавьте товар и верните его имя
        return null;
    }
}
