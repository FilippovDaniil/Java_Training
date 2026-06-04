/**
 * Задача 06 — Модуль 107: сидинг данных через @Sql
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Засейте связанные данные SQL-скриптом и проверьте связь:
 *     1) Создайте src/test/resources/seed-catalog.sql:
 *          INSERT INTO category06 (id, name) VALUES (1, 'Напитки');
 *          INSERT INTO product06 (id, name, category_id) VALUES (1, 'Кофе', 1);
 *          INSERT INTO product06 (id, name, category_id) VALUES (2, 'Чай', 1);
 *     2) @DataJpaTest; @Autowired CategoryRepository06 catRepo; @Autowired ProductRepository06 prodRepo.
 *     3) Метод seeded_relationship() пометьте @Sql("/seed-catalog.sql"):
 *          assertThat(prodRepo.count()).isEqualTo(2);
 *          assertThat(catRepo.findById(1L)).isPresent();
 *
 * ЦЕЛЬ: декларативно подготовить связанные данные через @Sql (вместо persist в коде).
 *
 * ЗАМЕТКА: в проектах с Flyway схема/данные накатываются миграциями и в тестах
 *          (V*.sql в src/test/resources). @Sql — для точечного сидинга конкретного теста.
 *
 * ВАЖНО: имена таблиц/колонок по умолчанию: category06, product06, FK-колонка category_id.
 *
 * ПОДСКАЗКА: путь к скрипту — от корня classpath ("/seed-catalog.sql").
 */
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

// --- сущности и репозитории (готовы) ---
@Entity
class Category06 {
    @Id
    private Long id;
    private String name;
    protected Category06() {}
}

@Entity
class Product06 {
    @Id
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category06 category;
    protected Product06() {}
}

interface CategoryRepository06 extends JpaRepository<Category06, Long> {}
interface ProductRepository06 extends JpaRepository<Product06, Long> {}

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class SqlSeedRelationshipTest06 {

    // TODO: @Autowired CategoryRepository06 catRepo;
    // TODO: @Autowired ProductRepository06 prodRepo;

    @Test
    // TODO: @Sql("/seed-catalog.sql")
    void seeded_relationship() {
        // TODO: assertThat(prodRepo.count()).isEqualTo(2);
        // TODO: assertThat(catRepo.findById(1L)).isPresent();
    }
}
