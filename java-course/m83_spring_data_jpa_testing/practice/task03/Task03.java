package m83_spring_data_jpa_testing.practice.task03;

/**
 * Задача 03 — Модуль 83: тестирование derived queries
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test, com.h2database:h2 (см. theory.md).
 *
 * ФОРМАТ: тест-класс (без main).
 *
 * ЗАДАНИЕ:
 *   Репозиторий ProductRepository03 содержит derived-методы (даны готовыми):
 *     findByPriceGreaterThan, findByCategoryOrderByPriceAsc, countByCategory.
 *
 *   1) @DataJpaTest; внедрить repo и TestEntityManager.
 *   2) @BeforeEach seed(): persist 4 товара (2 "Еда", 2 "Техника") с разными ценами,
 *      затем em.flush(); em.clear();
 *   3) Тест filters_by_price(): findByPriceGreaterThan(100) → проверить размер и что
 *      все цены > 100 (assertThat(list).allMatch(p -> p.getPrice() > 100)).
 *   4) Тест sorted_by_price(): findByCategoryOrderByPriceAsc("Еда") → цены идут по возрастанию
 *      (assertThat(...).isSortedAccordingTo(Comparator.comparingInt(Product03::getPrice))).
 *   5) Тест counts_by_category(): countByCategory("Техника") == 2.
 *
 * ЦЕЛЬ: покрыть тестами derived queries из модуля 79 на реальной (H2) БД.
 *
 * ПОДСКАЗКА: seed в @BeforeEach + flush()/clear() даёт каждому тесту чистые данные
 *            (между тестами @DataJpaTest откатывает транзакцию).
 */

import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task03 {

    // TODO: @Autowired
    ProductRepository03 repo;
    // TODO: @Autowired
    TestEntityManager em;

    @BeforeEach
    void seed() {
        // TODO: em.persist(new Product03("Хлеб", "Еда", 50));
        // TODO: em.persist(new Product03("Сыр", "Еда", 300));
        // TODO: em.persist(new Product03("Мышь", "Техника", 800));
        // TODO: em.persist(new Product03("Кабель", "Техника", 120));
        // TODO: em.flush(); em.clear();
    }

    @Test
    void filters_by_price() {
        // TODO: List<Product03> dear = repo.findByPriceGreaterThan(100);
        // TODO: assertThat(dear).allMatch(p -> p.getPrice() > 100);
    }

    @Test
    void sorted_by_price() {
        // TODO: List<Product03> food = repo.findByCategoryOrderByPriceAsc("Еда");
        // TODO: assertThat(food).isSortedAccordingTo(Comparator.comparingInt(Product03::getPrice));
    }

    @Test
    void counts_by_category() {
        // TODO: assertThat(repo.countByCategory("Техника")).isEqualTo(2);
    }
}
