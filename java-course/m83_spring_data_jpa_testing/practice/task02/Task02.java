package m83_spring_data_jpa_testing.practice.task02;

/**
 * Задача 02 — Модуль 83: TestEntityManager (persist + flush + clear)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test, com.h2database:h2 (см. theory.md).
 *
 * ФОРМАТ: тест-класс (без main).
 *
 * ЗАДАНИЕ:
 *   1) @DataJpaTest; внедрить ProductRepository02 и TestEntityManager.
 *   2) Тест find_by_name_containing():
 *        - em.persist(new Product02("Хлеб")); em.persist(new Product02("Хлебцы"));
 *          em.persist(new Product02("Молоко"));
 *        - em.flush();  // отправить INSERT'ы в БД
 *        - em.clear();  // очистить контекст — заставить repo читать из БД
 *        - List<Product02> found = repo.findByNameContaining("Хлеб");
 *        - assertThat(found).hasSize(2);
 *
 * ЦЕЛЬ: понять роль flush()/clear() — без них чтение может вернуться из кэша,
 *       и тест запроса ничего реально не проверит.
 *
 * ПОДСКАЗКА: TestEntityManager — это org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager.
 */

import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task02 {

    // TODO: @Autowired
    ProductRepository02 repo;

    // TODO: @Autowired
    TestEntityManager em;

    @Test
    void find_by_name_containing() {
        // TODO: em.persist(new Product02("Хлеб"));
        // TODO: em.persist(new Product02("Хлебцы"));
        // TODO: em.persist(new Product02("Молоко"));
        // TODO: em.flush();
        // TODO: em.clear();
        // TODO: List<Product02> found = repo.findByNameContaining("Хлеб");
        // TODO: assertThat(found).hasSize(2);
    }
}
