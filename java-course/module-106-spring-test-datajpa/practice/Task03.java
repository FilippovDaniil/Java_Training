/**
 * Задача 03 — Модуль 106: flush + clear — «холодное» чтение из БД
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Проверьте разницу чтения «из кэша» и «из БД». persistence context кэширует объект;
 *   чтобы прочитать настоящие данные из БД — очистите контекст:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired ProductRepository03 repository.
 *     2) cold_read_after_clear():
 *          Product03 p = em.persistAndFlush(new Product03("Кофе", 100));
 *          em.clear();                                       // выбросить из кэша 1 уровня
 *          Product03 fromDb = repository.findById(p.getId()).orElseThrow();
 *          assertThat(fromDb.getName()).isEqualTo("Кофе");   // реальный SELECT
 *          assertThat(fromDb).isNotSameAs(p);                // другой экземпляр → точно из БД
 *
 * ЦЕЛЬ: понять, что без clear() чтение возвращает закэшированный объект (маскирует баги маппинга).
 *
 * ВАЖНО: isNotSameAs (сравнение по ссылке!) доказывает, что объект пришёл из БД, а не из кэша.
 *
 * ПОДСКАЗКА: em.flush() отправляет SQL, em.clear() очищает persistence context.
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

// --- entity и репозиторий (готовы) ---
@Entity
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    protected Product03() {}
    Product03(String name, int price) { this.name = name; this.price = price; }
    Long getId() { return id; }
    String getName() { return name; }
}

interface ProductRepository03 extends JpaRepository<Product03, Long> {}

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class ColdReadTest03 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired ProductRepository03 repository;

    @Test
    void cold_read_after_clear() {
        // TODO: Product03 p = em.persistAndFlush(new Product03("Кофе", 100));
        // TODO: em.clear();
        // TODO: Product03 fromDb = repository.findById(p.getId()).orElseThrow();
        // TODO: assertThat(fromDb.getName()).isEqualTo("Кофе");
        // TODO: assertThat(fromDb).isNotSameAs(p);
    }
}
