package m82_spring_data_jpa_lazy_loading.practice.task01;

/**
 * Задача 01 — Модуль 82: связи и FetchType (LAZY/EAGER)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Свяжите Category01 (1) и Product01 (N):
 *        - в Category01: @OneToMany(mappedBy = "category") List<Product01> products
 *          (по умолчанию LAZY — оставьте так);
 *        - в Product01: @ManyToOne(fetch = FetchType.LAZY) Category01 category
 *          (ЯВНО LAZY — иначе @ManyToOne был бы EAGER).
 *   2) В CommandLineRunner: создайте категорию "Электроника", два товара в ней,
 *      сохраните; выведите products.size() для категории.
 *
 * ЦЕЛЬ: освоить двунаправленную связь и осознанный выбор FetchType.LAZY.
 *
 * ПОДСКАЗКА: владелец связи — сторона с @ManyToOne (колонка category_id).
 *            mappedBy на @OneToMany указывает имя поля-владельца.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
