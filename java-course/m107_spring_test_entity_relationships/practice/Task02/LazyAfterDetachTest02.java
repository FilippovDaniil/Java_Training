package m107_spring_test_entity_relationships.practice.task02;

/**
 * Задача 02 — Модуль 107: LazyInitializationException после детача
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Воспроизведите ошибку, которая на проде возникает при доступе к lazy-связи вне транзакции.
 *   В тесте имитируем «закрытие контекста» через em.detach:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired CategoryRepository02 repo.
 *     2) lazy_after_detach_fails():
 *          Long id = em.persistAndFlush(categoryWith2()).getId();
 *          em.clear();
 *          Category02 loaded = repo.findById(id).orElseThrow();
 *          em.detach(loaded);                                  // отсоединяем от контекста
 *          assertThatThrownBy(() -> loaded.getProducts().size())
 *                 .isInstanceOf(LazyInitializationException.class);
 *
 * ЦЕЛЬ: зафиксировать поведение — детач делает lazy-доступ невозможным (как вне транзакции на проде).
 *
 * ВАЖНО: прокси коллекции не может догрузиться без активного persistence context.
 *
 * ПОДСКАЗКА: помощник categoryWith2() создаёт категорию с двумя товарами (см. готовый код).
 */

import jakarta.persistence.*;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class LazyAfterDetachTest02 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired CategoryRepository02 repo;

    @Test
    void lazy_after_detach_fails() {
        // TODO: Long id = em.persistAndFlush(categoryWith2()).getId();
        // TODO: em.clear();
        // TODO: Category02 loaded = repo.findById(id).orElseThrow();
        // TODO: em.detach(loaded);
        // TODO: assertThatThrownBy(() -> loaded.getProducts().size())
        //              .isInstanceOf(LazyInitializationException.class);
    }

    private Category02 categoryWith2() {
        Category02 c = new Category02("Напитки");
        c.addProduct(new Product02("Кофе"));
        c.addProduct(new Product02("Чай"));
        return c;
    }
}
