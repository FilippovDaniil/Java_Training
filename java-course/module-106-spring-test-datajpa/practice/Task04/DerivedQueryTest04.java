/**
 * Задача 04 — Модуль 106: тест derived query
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Репозиторий имеет derived-методы (готов). Проверьте их на подготовленных данных:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired ProductRepository04 repository.
 *     2) finds_by_name_containing():
 *          em.persist(new Product04("Кофе арабика", 100));
 *          em.persist(new Product04("Чай зелёный", 50));
 *          em.flush();
 *          List<Product04> found = repository.findByNameContainingIgnoreCase("кофе");
 *          assertThat(found).hasSize(1).extracting(Product04::getName).containsExactly("Кофе арабика");
 *     3) finds_by_price_greater_than():
 *          assertThat(repository.findByPriceGreaterThan(60)).hasSize(1);
 *
 * ЦЕЛЬ: тестировать derived queries (модуль 79) на реальных данных через срез @DataJpaTest.
 *
 * ВАЖНО: подготовьте данные (persist + flush), затем вызовите метод репозитория и проверьте.
 *
 * ПОДСКАЗКА: extracting(Product04::getName) достаёт поле из каждого найденного элемента.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class DerivedQueryTest04 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired ProductRepository04 repository;

    @Test
    void finds_by_name_containing() {
        // TODO: em.persist(new Product04("Кофе арабика", 100));
        // TODO: em.persist(new Product04("Чай зелёный", 50));
        // TODO: em.flush();
        // TODO: List<Product04> found = repository.findByNameContainingIgnoreCase("кофе");
        // TODO: assertThat(found).hasSize(1).extracting(Product04::getName).containsExactly("Кофе арабика");
    }

    @Test
    void finds_by_price_greater_than() {
        // TODO: em.persist(new Product04("Дешёвый", 10));
        // TODO: em.persist(new Product04("Дорогой", 1000));
        // TODO: em.flush();
        // TODO: assertThat(repository.findByPriceGreaterThan(60)).hasSize(1);
    }
}
