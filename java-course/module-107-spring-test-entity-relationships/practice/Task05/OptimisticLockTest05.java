/**
 * Задача 05 — Модуль 107: оптимистическая блокировка (@Version) — тест конфликта
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Product05 имеет @Version (готов). Воспроизведите конфликт конкурентного обновления:
 *     1) @DataJpaTest; @Autowired TestEntityManager em; @Autowired ProductRepository05 repo.
 *     2) optimistic_lock_conflict():
 *          Long id = em.persistAndFlush(new Product05("Кофе", 100)).getId();   // version 0
 *          em.clear();
 *          Product05 first  = repo.findById(id).orElseThrow();   // version 0
 *          Product05 second = repo.findById(id).orElseThrow();   // version 0
 *          first.setPrice(120);  repo.saveAndFlush(first);       // version → 1, ОК
 *          second.setPrice(150);                                  // у second версия всё ещё 0
 *          assertThatThrownBy(() -> repo.saveAndFlush(second))
 *                 .isInstanceOf(OptimisticLockingFailureException.class);
 *
 * ЦЕЛЬ: тестировать защиту от потери обновления через @Version (модуль 90).
 *
 * ВАЖНО: второе сохранение с устаревшей версией → OptimisticLockingFailureException (Spring-обёртка).
 *
 * ПОДСКАЗКА: два независимых findById после clear имитируют «двух пользователей одновременно».
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class OptimisticLockTest05 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired ProductRepository05 repo;

    @Test
    void optimistic_lock_conflict() {
        // TODO: Long id = em.persistAndFlush(new Product05("Кофе", 100)).getId();
        // TODO: em.clear();
        // TODO: Product05 first  = repo.findById(id).orElseThrow();
        // TODO: Product05 second = repo.findById(id).orElseThrow();
        // TODO: first.setPrice(120); repo.saveAndFlush(first);
        // TODO: second.setPrice(150);
        // TODO: assertThatThrownBy(() -> repo.saveAndFlush(second))
        //              .isInstanceOf(OptimisticLockingFailureException.class);
    }
}
