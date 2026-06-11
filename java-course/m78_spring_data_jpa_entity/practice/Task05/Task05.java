package m78_spring_data_jpa_entity.practice.task05;

/**
 * Задача 05 — Модуль 78: @Embeddable / @Embedded — value object Money
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Вынесите цену в переиспользуемый value object.
 *   1) Класс Money05 пометьте @Embeddable. Поля: long amount, String currency.
 *      (НЕТ @Id и НЕТ своей таблицы — поля встроятся в таблицу владельца)
 *   2) В Product05 поле price (типа Money05) пометьте @Embedded.
 *   3) Запустите: в таблице products должны появиться колонки amount и currency
 *      (а не отдельная таблица money).
 *
 * ОЖИДАЕМЫЙ DDL (примерно):
 *   CREATE TABLE products (id ..., name ..., amount BIGINT, currency VARCHAR ...)
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем @Embeddable отличается от @Entity?
 *
 * ПОДСКАЗКА: @Embeddable не имеет идентичности — это объект-значение (равенство по значению).
 */

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
