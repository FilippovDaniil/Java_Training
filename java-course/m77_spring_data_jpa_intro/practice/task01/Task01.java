package m77_spring_data_jpa_intro.practice.task01;

/**
 * Задача 01 — Модуль 77: Первая JPA-сущность Product
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x,
 *   com.h2database:h2 (см. theory.md). Bare-javac НЕ компилируется.
 *
 * НАСТРОЙКА (application.properties):
 *   spring.jpa.hibernate.ddl-auto=create-drop
 *   spring.jpa.show-sql=true
 *
 * ЗАДАНИЕ:
 *   Превратите класс Product в JPA-сущность:
 *     1) Пометьте класс @Entity и @Table(name = "products").
 *     2) Поле id: @Id и @GeneratedValue(strategy = GenerationType.IDENTITY).
 *     3) Оставьте поля name (String) и price (long) как обычные колонки.
 *     4) Убедитесь, что есть конструктор БЕЗ аргументов (нужен JPA) —
 *        он уже объявлен (protected).
 *   5) Запустите приложение и проверьте в логах SQL: Hibernate создаст таблицу products.
 *
 * ЦЕЛЬ: понять минимальный набор аннотаций для сущности.
 *
 * ПОДСКАЗКА: import jakarta.persistence.*;
 */

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
