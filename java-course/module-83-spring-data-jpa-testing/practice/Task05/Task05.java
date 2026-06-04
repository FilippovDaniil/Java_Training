/**
 * Задача 05 — Модуль 83: тестирование пагинации и сортировки (Page)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test, com.h2database:h2 (см. theory.md).
 *
 * ФОРМАТ: тест-класс (без main).
 *
 * ЗАДАНИЕ:
 *   1) @DataJpaTest; repo + TestEntityManager; в @BeforeEach засеять 7 товаров
 *      "Товар-1".."Товар-7" с ценами 10..70, flush(), clear().
 *   2) Тест first_page(): repo.findAll(PageRequest.of(0, 3, Sort.by("price").descending()))
 *        - assertThat(page.getTotalElements()).isEqualTo(7);
 *        - assertThat(page.getTotalPages()).isEqualTo(3);
 *        - assertThat(page.getContent()).hasSize(3);
 *        - первая запись — с ценой 70 (самая дорогая).
 *   3) Тест last_page(): PageRequest.of(2, 3) → getContent().hasSize(1) (7 = 3+3+1),
 *        page.isLast() == true.
 *
 * ЦЕЛЬ: проверить Pageable/Page/Sort (модуль 79) тестом.
 *
 * ПОДСКАЗКА: Page<T> = findAll(Pageable). PageRequest.of(page, size, sort).
 */

import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import static org.assertj.core.api.Assertions.assertThat;

class Task05 {

    // TODO: @Autowired
    ProductRepository05 repo;
    // TODO: @Autowired
    TestEntityManager em;

    @BeforeEach
    void seed() {
        // TODO: for (int i = 1; i <= 7; i++) em.persist(new Product05("Товар-" + i, i * 10));
        // TODO: em.flush(); em.clear();
    }

    @Test
    void first_page() {
        // TODO: Page<Product05> page = repo.findAll(PageRequest.of(0, 3, Sort.by("price").descending()));
        // TODO: assertThat(page.getTotalElements()).isEqualTo(7);
        // TODO: assertThat(page.getTotalPages()).isEqualTo(3);
        // TODO: assertThat(page.getContent()).hasSize(3);
        // TODO: assertThat(page.getContent().get(0).getPrice()).isEqualTo(70);
    }

    @Test
    void last_page() {
        // TODO: Page<Product05> page = repo.findAll(PageRequest.of(2, 3, Sort.by("price")));
        // TODO: assertThat(page.getContent()).hasSize(1);
        // TODO: assertThat(page.isLast()).isTrue();
    }
}
