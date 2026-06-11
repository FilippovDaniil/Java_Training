package m81_spring_data_jpa_transactions.practice.task03;

/**
 * Задача 03 — Модуль 81: readOnly = true для читающих методов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В CatalogService03 разделите методы по назначению:
 *        - findAll():     @Transactional(readOnly = true)   — только чтение
 *        - countAll():    @Transactional(readOnly = true)
 *        - add(name):     @Transactional                    — изменение
 *   2) В CommandLineRunner добавьте 2 товара через add(...), затем выведите findAll/countAll.
 *
 * ВОПРОС (ответьте комментарием):
 *   Что даёт readOnly = true? Почему его НЕ ставят на методы, изменяющие данные?
 *
 * ПОДСКАЗКА: readOnly подсказывает Hibernate отключить dirty checking → быстрее на чтении.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
