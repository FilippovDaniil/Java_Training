/**
 * Задача 02 — Модуль 77: Первый репозиторий (JpaRepository)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Сущность Product02 готова.
 *   2) Объявите интерфейс репозитория:
 *        interface ProductRepository02 extends JpaRepository<Product02, Long> { }
 *      Реализацию писать НЕ нужно — Spring Data создаст её сам.
 *   3) В CommandLineRunner внедрите репозиторий и:
 *        - сохраните 2 товара через repo.save(...)
 *        - выведите repo.count()
 *        - выведите все имена через repo.findAll()
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Всего товаров: 2
 *   Кофе
 *   Чай
 *
 * ЦЕЛЬ: получить работающий CRUD без единой строки SQL и без реализации репозитория.
 *
 * ПОДСКАЗКА: JpaRepository<Сущность, ТипКлюча> уже даёт save/findById/findAll/count/delete.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
