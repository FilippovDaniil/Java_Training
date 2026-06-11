package m82_spring_data_jpa_lazy_loading.practice.task06;

/**
 * Задача 06 — Модуль 82: DTO-проекция вместо загрузки сущностей
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Когда view нужны лишь «имя категории + число товаров», грузить сущности и
 *   трогать ленивые связи незачем. Запросите сразу DTO одним агрегирующим запросом.
 *
 *   1) Объявлен record CategorySummary06(String name, long productCount).
 *   2) В CategoryRepository06 добавьте:
 *        @Query("select new CategorySummary06(c.name, count(p)) " +
 *               "from Category06 c left join c.products p group by c.id, c.name")
 *        List<CategorySummary06> summaries();
 *      (примечание: конструкторное выражение new в JPQL требует ПОЛНОГО имени класса,
 *       если он в пакете; здесь классы в default package — допустимо для шаблона курса.)
 *   3) report(): вызвать summaries() (БЕЗ @Transactional — сущностей нет, lazy не при чём)
 *      и вывести каждую строку.
 *
 * ЦЕЛЬ: понять, что DTO-проекция полностью снимает проблемы lazy/N+1 для read-моделей.
 *
 * ПОДСКАЗКА: нет управляемых сущностей → нет прокси → нет LazyInitializationException.
 *            left join — чтобы категории без товаров тоже попали (count = 0).
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
