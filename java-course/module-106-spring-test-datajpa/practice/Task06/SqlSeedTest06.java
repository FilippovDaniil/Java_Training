/**
 * Задача 06 — Модуль 106: @Sql-сидинг и заметка о реальной БД (@AutoConfigureTestDatabase)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Засейте данные SQL-скриптом перед тестом вместо ручного persist:
 *     1) Создайте src/test/resources/seed-products.sql:
 *          INSERT INTO product06 (name, price) VALUES ('Кофе', 100);
 *          INSERT INTO product06 (name, price) VALUES ('Чай', 50);
 *     2) @DataJpaTest; @Autowired ProductRepository06 repository.
 *     3) Метод seeded_count() пометьте @Sql("/seed-products.sql"):
 *          assertThat(repository.count()).isEqualTo(2);
 *          assertThat(repository.findByName("Кофе")).isPresent();
 *
 * ЦЕЛЬ: освоить @Sql для декларативного сидинга тестовых данных (альтернатива persist в коде).
 *
 * ЗАМЕТКА: чтобы тестировать на РЕАЛЬНОЙ СУБД (не H2), добавьте
 *          @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *          и поднимите БД (обычно Testcontainers — модуль 109).
 *
 * ВАЖНО: имя таблицы по умолчанию = имя класса в нижнем регистре (product06).
 *
 * ПОДСКАЗКА: @Sql можно вешать на класс (для всех тестов) или на отдельный метод.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.jdbc.Sql;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class SqlSeedTest06 {

    // TODO: @Autowired ProductRepository06 repository;

    @Test
    // TODO: @Sql("/seed-products.sql")
    void seeded_count() {
        // TODO: assertThat(repository.count()).isEqualTo(2);
        // TODO: assertThat(repository.findByName("Кофе")).isPresent();
    }
}
