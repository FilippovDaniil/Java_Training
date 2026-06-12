package m107_spring_test_entity_relationships.practice.task04;

/**
 * Задача 04 — Модуль 107: каскады (cascade PERSIST/ALL) и orphanRemoval
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Связь Category04 → Product04 настроена cascade=ALL, orphanRemoval=true (готово). Проверьте:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired ProductRepository04 productRepo.
 *     2) cascade_persists_children():
 *          Category04 c = new Category04("Напитки");
 *          c.addProduct(new Product04("Кофе")); c.addProduct(new Product04("Чай"));
 *          em.persistAndFlush(c);   // товары сохранятся каскадом
 *          em.clear();
 *          assertThat(productRepo.count()).isEqualTo(2);
 *     3) orphan_removal_deletes_child():
 *          Category04 c = em.persistAndFlush(categoryWith2());
 *          c.getProducts().remove(0);          // убрали из коллекции → orphan
 *          em.persistAndFlush(c);
 *          em.clear();
 *          assertThat(productRepo.count()).isEqualTo(1);   // удалён каскадно
 *
 * ЦЕЛЬ: тестировать каскадное сохранение и удаление осиротевших детей (модули 87/89).
 *
 * ВАЖНО: orphanRemoval удаляет ребёнка при ИСКЛЮЧЕНИИ его из коллекции родителя.
 *
 * ПОДСКАЗКА: проверяйте count() дочернего репозитория до/после операций.
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
class CascadeTest04 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired ProductRepository04 productRepo;

    @Test
    void cascade_persists_children() {
        // TODO: Category04 c = new Category04("Напитки");
        // TODO: c.addProduct(new Product04("Кофе")); c.addProduct(new Product04("Чай"));
        // TODO: em.persistAndFlush(c);
        // TODO: em.clear();
        // TODO: assertThat(productRepo.count()).isEqualTo(2);
    }

    @Test
    void orphan_removal_deletes_child() {
        // TODO: Category04 c = em.persistAndFlush(categoryWith2());
        // TODO: c.getProducts().remove(0);
        // TODO: em.persistAndFlush(c);
        // TODO: em.clear();
        // TODO: assertThat(productRepo.count()).isEqualTo(1);
    }

    private Category04 categoryWith2() {
        Category04 c = new Category04("Напитки");
        c.addProduct(new Product04("Кофе"));
        c.addProduct(new Product04("Чай"));
        return c;
    }
}
