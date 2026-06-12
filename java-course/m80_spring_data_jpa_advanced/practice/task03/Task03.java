package m80_spring_data_jpa_advanced.practice.task03;

/**
 * Задача 03 — Модуль 80: Интерфейсная проекция (выбрать часть полей)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Не тяните всю сущность — верните только имя и цену через интерфейсную проекцию.
 *   1) Объявите интерфейс-проекцию:
 *        interface ProductView03 { String getName(); long getPrice(); }
 *   2) В репозитории derived-метод, возвращающий проекцию:
 *        List<ProductView03> findByCategory(String category);
 *   3) В CommandLineRunner выведите имя и цену каждой проекции.
 *
 * ЦЕЛЬ: вернуть «срез» данных — это быстрее, чем грузить полную сущность.
 *
 * ПОДСКАЗКА: имена геттеров проекции должны совпадать с полями сущности (getName ↔ name).
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
