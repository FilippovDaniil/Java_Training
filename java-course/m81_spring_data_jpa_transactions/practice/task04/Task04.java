package m81_spring_data_jpa_transactions.practice.task04;

/**
 * Задача 04 — Модуль 81: Правила отката (rollbackFor для checked-исключений)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Покажите разницу отката для unchecked и checked исключений.
 *   1) saveAndFailUnchecked(name): @Transactional. Сохранить товар, затем
 *      throw new RuntimeException("сбой") → транзакция ОТКАТИТСЯ (товара не будет).
 *   2) saveAndFailChecked(name): @Transactional. Сохранить товар, затем
 *      throw new Exception("сбой") (checked) → по умолчанию транзакция НЕ откатывается!
 *      Чтобы откатывалась — добавьте @Transactional(rollbackFor = Exception.class).
 *   3) В CommandLineRunner вызовите оба метода (в try/catch), затем выведите repo.count()
 *      и проверьте, какие записи остались.
 *
 * ЦЕЛЬ: понять, что checked-исключение по умолчанию НЕ откатывает транзакцию.
 *
 * ПОДСКАЗКА: RuntimeException → откат; checked Exception → коммит (если нет rollbackFor).
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
