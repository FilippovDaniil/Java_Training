/**
 * Задача 07 — Модуль 83: МИНИ-ПРОЕКТ «Тест-сьют репозитория shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test, com.h2database:h2 (см. theory.md).
 *   (опционально для варианта на реальной БД: org.testcontainers:postgresql, org.testcontainers:junit-jupiter)
 *
 * ФОРМАТ: тест-класс (без main). Запуск: ./gradlew test (или ▶ в IDE).
 *
 * ЦЕЛЬ: написать полноценный набор тестов ProductRepository07, покрывающий
 *       CRUD + derived query + кастомный @Query + пагинацию, с сидингом через
 *       TestEntityManager и ассертами AssertJ. Это эталон тестирования слоя данных.
 *
 * МОДЕЛЬ (дана готовой):
 *   Product07(id, name, category, price) + ProductRepository07 с методами:
 *     findByCategory(String), findByPriceLessThanEqual(int),
 *     @Query countCheap(maxPrice), findAll(Pageable).
 *
 * ЗАДАНИЕ — реализуйте тесты (минимум 6 @Test):
 *   @DataJpaTest, @Autowired repo + TestEntityManager.
 *   @BeforeEach seed(): persist 5 товаров (3 "Еда": 40/60/350, 2 "Техника": 800/120),
 *       em.flush(); em.clear();
 *
 *   1) crud_create_read():     save → findById присутствует, поля совпадают.
 *   2) crud_update():          загрузить, изменить цену, save → перечитать (flush/clear),
 *                              цена обновилась.
 *   3) crud_delete():          deleteById → findById пуст, count уменьшился.
 *   4) derived_by_category():  findByCategory("Еда") → размер 3, все категории "Еда".
 *   5) derived_by_price():     findByPriceLessThanEqual(120) → все цены <= 120.
 *   6) custom_count_cheap():   countCheap(100) → число товаров с ценой < 100 (= 2: 40 и 60).
 *   7) paging():               findAll(PageRequest.of(0,2,Sort.by("price"))) → totalElements 5,
 *                              content size 2, первый — самый дешёвый (40).
 *
 * ВАРИАНТ НА РЕАЛЬНОЙ БД (комментарий-задание, по желанию):
 *   Скопируйте класс, добавьте:
 *     @AutoConfigureTestDatabase(replace = Replace.NONE)
 *     @Testcontainers
 *     static @Container @ServiceConnection PostgreSQLContainer<?> pg =
 *         new PostgreSQLContainer<>("postgres:16");
 *   и запустите те же тесты на настоящем Postgres (нужен Docker).
 *
 * ПОДСКАЗКА: соберите вместе приёмы Task01–Task05; flush()/clear() перед перечитыванием
 *            в update-тесте обязателен, иначе вернётся объект из кэша контекста.
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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task07 {

    // TODO: @Autowired
    ProductRepository07 repo;
    // TODO: @Autowired
    TestEntityManager em;

    @BeforeEach
    void seed() {
        // TODO: em.persist(new Product07("Хлеб", "Еда", 40));
        // TODO: em.persist(new Product07("Молоко", "Еда", 60));
        // TODO: em.persist(new Product07("Сыр", "Еда", 350));
        // TODO: em.persist(new Product07("Мышь", "Техника", 800));
        // TODO: em.persist(new Product07("Кабель", "Техника", 120));
        // TODO: em.flush(); em.clear();
    }

    @Test
    void crud_create_read() {
        // TODO
    }

    @Test
    void crud_update() {
        // TODO: загрузить, setPrice, save; em.flush(); em.clear(); перечитать — цена новая
    }

    @Test
    void crud_delete() {
        // TODO
    }

    @Test
    void derived_by_category() {
        // TODO: assertThat(repo.findByCategory("Еда")).hasSize(3).allMatch(p -> p.getCategory().equals("Еда"));
    }

    @Test
    void derived_by_price() {
        // TODO: assertThat(repo.findByPriceLessThanEqual(120)).allMatch(p -> p.getPrice() <= 120);
    }

    @Test
    void custom_count_cheap() {
        // TODO: assertThat(repo.countCheap(100)).isEqualTo(2);
    }

    @Test
    void paging() {
        // TODO: Page<Product07> page = repo.findAll(PageRequest.of(0, 2, Sort.by("price")));
        // TODO: assertThat(page.getTotalElements()).isEqualTo(5);
        // TODO: assertThat(page.getContent()).hasSize(2);
        // TODO: assertThat(page.getContent().get(0).getPrice()).isEqualTo(40);
    }
}
