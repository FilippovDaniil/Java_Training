/**
 * Задача 04 — Модуль 83: тестирование @Query (JPQL) и DTO-проекции
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-data-jpa, spring-boot-starter-test, com.h2database:h2 (см. theory.md).
 *
 * ФОРМАТ: тест-класс (без main).
 *
 * ЗАДАНИЕ:
 *   Репозиторий ProductRepository04 содержит (даны готовыми):
 *     - @Query("select p from Product04 p where p.price between :lo and :hi") List findInPriceRange(...)
 *     - @Query("select new PriceStat04(p.category, avg(p.price)) from Product04 p group by p.category")
 *       List<PriceStat04> avgPriceByCategory()
 *
 *   1) @DataJpaTest; внедрить repo и TestEntityManager; в @BeforeEach засеять 4 товара
 *      (2 "Еда", 2 "Техника"), flush(), clear().
 *   2) Тест in_price_range(): findInPriceRange(100, 500) — проверить, что все цены в диапазоне.
 *   3) Тест avg_by_category(): avgPriceByCategory() возвращает 2 строки;
 *      найдите строку "Еда" и проверьте среднюю цену
 *      (assertThat(...).extracting(PriceStat04::category).contains("Еда","Техника")).
 *
 * ЦЕЛЬ: убедиться, что JPQL и конструкторная проекция (модуль 80) работают на реальной БД.
 *
 * ПОДСКАЗКА: avg(...) в JPQL возвращает Double — поле avg в record сделайте double.
 */
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: @DataJpaTest
class Task04 {

    // TODO: @Autowired
    ProductRepository04 repo;
    // TODO: @Autowired
    TestEntityManager em;

    @BeforeEach
    void seed() {
        // TODO: persist 4 товара (Хлеб/Еда/50, Сыр/Еда/300, Мышь/Техника/800, Кабель/Техника/120)
        // TODO: em.flush(); em.clear();
    }

    @Test
    void in_price_range() {
        // TODO: List<Product04> mid = repo.findInPriceRange(100, 500);
        // TODO: assertThat(mid).allMatch(p -> p.getPrice() >= 100 && p.getPrice() <= 500);
    }

    @Test
    void avg_by_category() {
        // TODO: List<PriceStat04> stats = repo.avgPriceByCategory();
        // TODO: assertThat(stats).hasSize(2)
        // TODO:        .extracting(PriceStat04::category).contains("Еда", "Техника");
    }
}

// --- DTO для проекции ---
record PriceStat04(String category, double avgPrice) {}

// --- класс-под-тестом дан готовым ---
@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private int price;
    protected Product04() {}
    public Product04(String name, String category, int price) {
        this.name = name; this.category = category; this.price = price;
    }
    public int getPrice() { return price; }
}

interface ProductRepository04 extends JpaRepository<Product04, Long> {

    @Query("select p from Product04 p where p.price between :lo and :hi")
    List<Product04> findInPriceRange(@Param("lo") int lo, @Param("hi") int hi);

    @Query("select new PriceStat04(p.category, avg(p.price)) from Product04 p group by p.category")
    List<PriceStat04> avgPriceByCategory();
}
