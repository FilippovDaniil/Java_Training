/**
 * Задача 01 — Модуль 106: @DataJpaTest — сохранение и поиск через репозиторий
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Entity Product01 и репозиторий ProductRepository01 готовы (ниже). Напишите slice-тест:
 *     1) Класс пометьте @DataJpaTest; @Autowired ProductRepository01 repository.
 *     2) saves_and_finds():
 *          Product01 saved = repository.save(new Product01("Кофе", 100));
 *          assertThat(saved.getId()).isNotNull();
 *          assertThat(repository.findById(saved.getId())).isPresent();
 *     3) count_after_two():
 *          repository.save(new Product01("Чай", 50));
 *          assertThat(repository.count()).isEqualTo(2);   // данные откатятся после теста
 *
 * ЦЕЛЬ: освоить @DataJpaTest — лёгкий срез для репозиториев, с авто-откатом транзакции.
 *
 * ВАЖНО: @DataJpaTest по умолчанию использует встроенную H2 и откатывает данные после теста.
 *
 * ПОДСКАЗКА: save возвращает сущность с присвоенным id (стратегия IDENTITY).
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.assertj.core.api.Assertions.assertThat;

// --- entity и репозиторий (готовы) ---
@Entity
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    protected Product01() {}
    Product01(String name, int price) { this.name = name; this.price = price; }
    Long getId() { return id; }
    String getName() { return name; }
    int getPrice() { return price; }
}

interface ProductRepository01 extends JpaRepository<Product01, Long> {}

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class ProductRepositoryTest01 {

    // TODO: @Autowired ProductRepository01 repository;

    @Test
    void saves_and_finds() {
        // TODO: Product01 saved = repository.save(new Product01("Кофе", 100));
        // TODO: assertThat(saved.getId()).isNotNull();
        // TODO: assertThat(repository.findById(saved.getId())).isPresent();
    }

    @Test
    void count_after_two() {
        // TODO: repository.save(new Product01("Кофе", 100));
        // TODO: repository.save(new Product01("Чай", 50));
        // TODO: assertThat(repository.count()).isEqualTo(2);
    }
}
