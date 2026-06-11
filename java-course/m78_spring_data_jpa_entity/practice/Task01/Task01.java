package m78_spring_data_jpa_entity.practice.task01;

/**
 * Задача 01 — Модуль 78: Сущность с генерацией ключа
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Превратите Customer01 в сущность: @Entity, @Table(name = "customers").
 *   2) Поле id: @Id и @GeneratedValue(strategy = GenerationType.IDENTITY).
 *   3) Запустите приложение с ddl-auto=create-drop, show-sql=true — посмотрите
 *      сгенерированный DDL (CREATE TABLE customers ...).
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем IDENTITY отличается от SEQUENCE? Когда какую стратегию выбирают?
 *   (подсказка — таблица в theory.md)
 *
 * ПОДСКАЗКА: для H2/MySQL обычно IDENTITY, для PostgreSQL/Oracle — SEQUENCE.
 */

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
