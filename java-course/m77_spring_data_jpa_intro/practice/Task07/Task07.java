package m77_spring_data_jpa_intro.practice.task07;

/**
 * Задача 07 — Модуль 77: МИНИ-ПРОЕКТ «Старт shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * НАСТРОЙКА (application.properties):
 *   spring.jpa.hibernate.ddl-auto=create-drop
 *   spring.jpa.show-sql=true
 *
 * ЦЕЛЬ: заложить основу сквозного проекта shop-data-jpa — сущности Product и Category,
 *       их репозитории, сервис и сидинг при старте. На этом проекте строятся модули 78–84.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Сущности:
 *        Category07: @Entity, @Id @GeneratedValue, String name.
 *        Product07:  @Entity, @Id @GeneratedValue, String name, long price, String category
 *                    (пока просто строка-категория; связи @ManyToOne — в модуле 78/87).
 *
 *   2) Репозитории:
 *        CategoryRepository07 extends JpaRepository<Category07, Long>
 *        ProductRepository07  extends JpaRepository<Product07, Long>
 *
 *   3) CatalogService07 (@Service):
 *        - seed(): сохранить категории "Электроника","Книги" и товары
 *                  "Ноутбук"(80000,"Электроника"), "Мышь"(900,"Электроника"), "Java book"(1500,"Книги")
 *        - productCount() → repo.count()
 *        - categoryCount() → repo.count()
 *
 *   4) StartupRunner07 (CommandLineRunner):
 *        вызвать seed(), вывести "Товаров: X, категорий: Y".
 *
 * АРХИТЕКТУРА:
 *
 *   StartupRunner07 ──► CatalogService07 ──► ProductRepository07  ──► таблица products
 *                                       └──► CategoryRepository07 ──► таблица categories
 *
 * ПОДСКАЗКА: это фундамент — в следующих модулях добавим маппинги, связи и запросы.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
