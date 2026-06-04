/**
 * Задача 05 — Модуль 106: тест @Query (JPQL)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Репозиторий содержит @Query JPQL (готов). Проверьте корректность запроса:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired ProductRepository05 repository.
 *     2) custom_query_filters_by_price():
 *          em.persist(new Product05("Дешёвый", 10));
 *          em.persist(new Product05("Дорогой", 1000));
 *          em.flush();
 *          assertThat(repository.findExpensive(100))
 *                 .extracting(Product05::getName).containsExactly("Дорогой");
 *     3) count_query():
 *          assertThat(repository.countCheaperThan(100)).isEqualTo(1);
 *
 * ЦЕЛЬ: тестировать @Query-методы (JPQL) — модуль 80 — на срезе данных.
 *
 * ВАЖНО: @Query с :param проверяется так же, как derived — на подготовленных данных.
 *
 * ПОДСКАЗКА: тест на запрос — лучшая защита от опечаток в JPQL (упадёт при ошибке грамматики).
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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class QueryTest05 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired ProductRepository05 repository;

    @Test
    void custom_query_filters_by_price() {
        // TODO: em.persist(new Product05("Дешёвый", 10));
        // TODO: em.persist(new Product05("Дорогой", 1000));
        // TODO: em.flush();
        // TODO: assertThat(repository.findExpensive(100))
        //              .extracting(Product05::getName).containsExactly("Дорогой");
    }

    @Test
    void count_query() {
        // TODO: em.persist(new Product05("Дешёвый", 10));
        // TODO: em.persist(new Product05("Дорогой", 1000));
        // TODO: em.flush();
        // TODO: assertThat(repository.countCheaperThan(100)).isEqualTo(1);
    }
}
