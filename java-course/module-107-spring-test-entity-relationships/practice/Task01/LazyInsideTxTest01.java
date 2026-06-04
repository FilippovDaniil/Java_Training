/**
 * Задача 01 — Модуль 107: lazy loading внутри транзакции теста (работает)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Category01 1—N Product01 (LAZY, готово). В @DataJpaTest контекст открыт всю транзакцию —
 *   значит lazy-доступ внутри теста срабатывает:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired CategoryRepository01 repo.
 *     2) lazy_access_inside_tx_ok():
 *          Category01 c = new Category01("Напитки");
 *          c.addProduct(new Product01("Кофе")); c.addProduct(new Product01("Чай"));
 *          Long id = em.persistAndFlush(c).getId();
 *          em.clear();
 *          Category01 loaded = repo.findById(id).orElseThrow();
 *          assertThat(loaded.getProducts()).hasSize(2);   // lazy-инициализация: контекст открыт → ОК
 *
 * ЦЕЛЬ: увидеть, что в транзакционном тесте lazy работает (контекст не закрыт).
 *
 * ВАЖНО: это «обманчиво» — на проде вне транзакции тот же доступ упадёт (см. задачу 02).
 *
 * ПОДСКАЗКА: addProduct связывает обе стороны (см. готовый код сущности).
 */

import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class LazyInsideTxTest01 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired CategoryRepository01 repo;

    @Test
    void lazy_access_inside_tx_ok() {
        // TODO: Category01 c = new Category01("Напитки");
        // TODO: c.addProduct(new Product01("Кофе")); c.addProduct(new Product01("Чай"));
        // TODO: Long id = em.persistAndFlush(c).getId();
        // TODO: em.clear();
        // TODO: Category01 loaded = repo.findById(id).orElseThrow();
        // TODO: assertThat(loaded.getProducts()).hasSize(2);
    }
}
