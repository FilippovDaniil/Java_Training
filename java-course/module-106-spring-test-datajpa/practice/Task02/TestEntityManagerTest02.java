/**
 * Задача 02 — Модуль 106: TestEntityManager — подготовка данных
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Используйте TestEntityManager для подготовки данных (вместо репозитория):
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired ProductRepository02 repository.
 *     2) persisted_via_em_visible_to_repo():
 *          Product02 p = em.persistAndFlush(new Product02("Кофе", 100));   // INSERT, id присвоен
 *          assertThat(p.getId()).isNotNull();
 *          assertThat(repository.findById(p.getId())).isPresent();          // репозиторий видит запись
 *     3) find_via_em():
 *          Product02 p = em.persistAndFlush(new Product02("Чай", 50));
 *          assertThat(em.find(Product02.class, p.getId()).getName()).isEqualTo("Чай");
 *
 * ЦЕЛЬ: научиться готовить тестовые данные через TestEntityManager (persistAndFlush/find).
 *
 * ВАЖНО: persistAndFlush сразу выполняет INSERT → id доступен (важно для IDENTITY).
 *
 * ПОДСКАЗКА: данные, записанные em, видны репозиторию в той же транзакции теста.
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
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class TestEntityManagerTest02 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired ProductRepository02 repository;

    @Test
    void persisted_via_em_visible_to_repo() {
        // TODO: Product02 p = em.persistAndFlush(new Product02("Кофе", 100));
        // TODO: assertThat(p.getId()).isNotNull();
        // TODO: assertThat(repository.findById(p.getId())).isPresent();
    }

    @Test
    void find_via_em() {
        // TODO: Product02 p = em.persistAndFlush(new Product02("Чай", 50));
        // TODO: assertThat(em.find(Product02.class, p.getId()).getName()).isEqualTo("Чай");
    }
}
