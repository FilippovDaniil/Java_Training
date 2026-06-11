package m77_spring_data_jpa_intro.practice.task05;

/**
 * Задача 05 — Модуль 77: Вторая сущность Category и наблюдение схемы
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * НАСТРОЙКА: spring.jpa.hibernate.ddl-auto=create-drop, spring.jpa.show-sql=true,
 *            spring.h2.console.enabled=true
 *
 * ЗАДАНИЕ:
 *   1) Доведите Category05 до сущности: @Entity, @Id @GeneratedValue, поле name.
 *   2) Объявите репозиторий CategoryRepository05 extends JpaRepository<Category05, Long>.
 *   3) В CommandLineRunner сохраните 3 категории ("Электроника","Книги","Одежда")
 *      и выведите их количество.
 *   4) Запустите. В логах Hibernate должна появиться команда создания таблицы categories.
 *      (Дополнительно: откройте http://localhost:8080/h2-console и посмотрите таблицу.)
 *
 * ВОПРОС (ответьте комментарием):
 *   Что делает ddl-auto=create-drop и почему это удобно для разработки, но опасно для prod?
 *
 * ПОДСКАЗКА: каждая @Entity → отдельная таблица; имя по умолчанию = имя класса.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
