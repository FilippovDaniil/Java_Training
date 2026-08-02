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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class Task02Test {

    // TODO: @Autowired
    private final ProductRepository02 repo;

    // TODO: @Autowired
    private final TestEntityManager em;

    @Autowired
    public Task02Test(ProductRepository02 repo, TestEntityManager em) {
        this.repo = repo;
        this.em = em;
    }

    @Test
    void find_by_name_containing() {
        // TODO: em.persist(new Product02("Хлеб"));
        // TODO: em.persist(new Product02("Хлебцы"));
        // TODO: em.persist(new Product02("Молоко"));
        // TODO: em.flush();
        // TODO: em.clear();
        // TODO: List<Product02> found = repo.findByNameContaining("Хлеб");
        // TODO: assertThat(found).hasSize(2);
        em.persist(new Product02("Хлеб"));
        em.persist(new Product02("Хлебцы"));
        em.persist(new Product02("Молоко"));
        em.flush();
        em.clear();
        List<Product02> found = repo.findByNameContaining("Хлеб");
        assertThat(found).hasSize(2);
    }
}
