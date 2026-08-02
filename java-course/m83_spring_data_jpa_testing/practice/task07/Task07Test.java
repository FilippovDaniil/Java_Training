package m83_spring_data_jpa_testing.practice.task07;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class Task07Test {

    // TODO: @Autowired
    private final ProductRepository07 repo;
    // TODO: @Autowired
    private final TestEntityManager em;

    @Autowired
    public Task07Test(ProductRepository07 repo, TestEntityManager em) {
        this.repo = repo;
        this.em = em;
    }

    @BeforeEach
    void seed() {
        // TODO: em.persist(new Product07("Хлеб", "Еда", 40));
        // TODO: em.persist(new Product07("Молоко", "Еда", 60));
        // TODO: em.persist(new Product07("Сыр", "Еда", 350));
        // TODO: em.persist(new Product07("Мышь", "Техника", 800));
        // TODO: em.persist(new Product07("Кабель", "Техника", 120));
        // TODO: em.flush(); em.clear();
        em.persist(new Product07("Хлеб", "Еда", 40));
        em.persist(new Product07("Молоко", "Еда", 60));
        em.persist(new Product07("Сыр", "Еда", 350));
        em.persist(new Product07("Мышь", "Техника", 800));
        em.persist(new Product07("Кабель", "Техника", 120));
        em.flush();
        em.clear();
    }

    @Test
    void crud_create_read() {
        Product07 newProduct = new Product07("Ноутбук", "Техника", 1500);
        Product07 saved = repo.save(newProduct);
        em.flush();
        em.clear();

        Product07 found = repo.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("Ноутбук");
        assertThat(found.getCategory()).isEqualTo("Техника");
        assertThat(found.getPrice()).isEqualTo(1500);
    }

    @Test
    void crud_update() {
        // Загружаем первый продукт
        Product07 product = repo.findAll().get(0);
        product.setPrice(999);
        repo.save(product);
        em.flush();
        em.clear();

        Product07 updated = repo.findById(product.getId()).orElseThrow();
        assertThat(updated.getPrice()).isEqualTo(999);
    }

    @Test
    void crud_delete() {
        Product07 product = repo.findAll().get(0);
        Long id = product.getId();
        repo.delete(product);
        em.flush();
        em.clear();

        assertThat(repo.findById(id)).isEmpty();
    }

    @Test
    void derived_by_category() {
        List<Product07> products = repo.findByCategory("Еда");
        assertThat(products).hasSize(3);
        assertThat(products).allMatch(p -> p.getCategory().equals("Еда"));
    }

    @Test
    void derived_by_price() {
        List<Product07> products = repo.findByPriceLessThanEqual(120);
        assertThat(products).allMatch(p -> p.getPrice() <= 120);
        // Дополнительно проверим, что есть именно 3 продукта: Хлеб(40), Молоко(60), Кабель(120)
        assertThat(products).hasSize(3);
    }

    @Test
    void custom_count_cheap() {
        long count = repo.countCheap(100);
        assertThat(count).isEqualTo(2); // Хлеб(40) и Молоко(60)
    }

    @Test
    void paging() {
        Page<Product07> page = repo.findAll(PageRequest.of(0, 2, Sort.by("price")));
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent().get(0).getPrice()).isEqualTo(40); // Самый дешёвый
    }
}
