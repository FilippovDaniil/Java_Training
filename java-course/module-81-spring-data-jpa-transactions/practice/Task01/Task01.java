/**
 * Задача 01 — Модуль 81: @Transactional на сервисном методе
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) AccountService01.transfer(fromId, toId, amount) пометьте @Transactional.
 *      Внутри: снять amount с одного счёта, добавить другому, сохранить оба.
 *      Если на счёте-источнике не хватает средств — throw new IllegalStateException(...)
 *      (тогда вся операция откатится).
 *   2) В CommandLineRunner: создайте два счёта, выполните успешный перевод,
 *      затем неуспешный (слишком большой) — поймайте исключение и проверьте,
 *      что балансы НЕ изменились (откат).
 *
 * ЦЕЛЬ: убедиться, что @Transactional делает операцию атомарной (всё или ничего).
 *
 * ПОДСКАЗКА: @Transactional ставят на СЕРВИС, не на репозиторий.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
