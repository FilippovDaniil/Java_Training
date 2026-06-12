package m77_spring_data_jpa_intro.practice.task06;

/**
 * Задача 06 — Модуль 77: Сервисный слой поверх репозитория
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Правильная архитектура: Контроллер/Runner → Сервис → Репозиторий → БД.
 *   1) ProductService06 (@Service) внедряет ProductRepository06.
 *        - addProduct(name, price): сохранить и вернуть Product06
 *        - findExpensive(min): вернуть из findAll() только товары с price >= min (через Stream)
 *        - total(): вернуть repo.count()
 *   2) В CommandLineRunner используйте СЕРВИС (не репозиторий напрямую):
 *        добавьте 3 товара, выведите дорогие (>= 400) и общее число.
 *
 * ЦЕЛЬ: закрепить слоистую архитектуру — бизнес-логика в сервисе, доступ к данным в репозитории.
 *
 * ПОДСКАЗКА: репозиторий внедряется в сервис, сервис — в Runner/контроллер.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
