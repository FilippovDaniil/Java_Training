/**
 * Задача 01 — Модуль 79: Базовый CRUD через JpaRepository
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) ProductRepository01 extends JpaRepository<Product01, Long> (готов — пустой).
 *   2) В CommandLineRunner используйте ТОЛЬКО готовые методы JpaRepository:
 *        - saveAll(List.of(...)) — сохранить 3 товара
 *        - count()              — вывести количество
 *        - findById(1L)         — вывести имя первого (Optional!)
 *        - existsById(1L)       — вывести true/false
 *        - deleteById(1L), затем снова count()
 *
 * ЦЕЛЬ: освоить «бесплатный» CRUD без единого собственного метода.
 *
 * ПОДСКАЗКА: findById возвращает Optional — .orElseThrow() или .map(...).
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
