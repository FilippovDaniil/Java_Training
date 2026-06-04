/**
 * Задача 06 — Модуль 83: подготовка данных через @Sql и изоляция тестов (rollback)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test, com.h2database:h2 (см. theory.md).
 *
 * ФОРМАТ: тест-класс (без main).
 *
 * ЗАДАНИЕ:
 *   ЧАСТЬ A — @Sql:
 *     1) Создайте файл src/test/resources/test-data/products06.sql со строками вида:
 *          INSERT INTO products (name, price) VALUES ('Молоко', 60);
 *          INSERT INTO products (name, price) VALUES ('Хлеб', 40);
 *          INSERT INTO products (name, price) VALUES ('Сыр', 350);
 *     2) Тест seeded_by_sql(): пометьте @Sql("/test-data/products06.sql"),
 *        проверьте repo.count() == 3.
 *
 *   ЧАСТЬ B — изоляция (rollback между тестами):
 *     3) Тест adds_one_then_isolated_a(): repo.save(new Product06("X", 1)); count() == 1.
 *     4) Тест adds_one_then_isolated_b(): СРАЗУ проверьте count() == 0
 *        (изменения теста A откатились — @DataJpaTest откатывает каждую транзакцию).
 *
 * ЦЕЛЬ: освоить @Sql-сидинг и убедиться, что тесты не пачкают друг друга.
 *
 * ПОДСКАЗКА: путь @Sql — от корня classpath (начинается с "/"). Имена тестов A/B
 *            подобраны так, чтобы порядок не влиял: каждый стартует с пустой БД.
 */

import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

class Task06 {

    // TODO: @Autowired
    ProductRepository06 repo;

    @Test
    // TODO: @Sql("/test-data/products06.sql")
    void seeded_by_sql() {
        // TODO: assertThat(repo.count()).isEqualTo(3);
    }

    @Test
    void adds_one_then_isolated_a() {
        // TODO: repo.save(new Product06("X", 1));
        // TODO: assertThat(repo.count()).isEqualTo(1);
    }

    @Test
    void adds_one_then_isolated_b() {
        // TODO: assertThat(repo.count()).isEqualTo(0);   // изменения теста A откатились
    }
}
