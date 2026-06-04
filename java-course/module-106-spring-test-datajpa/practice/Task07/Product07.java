/**
 * Задача 07 — Модуль 106: МИНИ-ПРОЕКТ «Полный тест-сьют репозитория shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: покрыть @DataJpaTest'ом весь репозиторий товаров: CRUD, derived, @Query, пагинация,
 *       холодное чтение. Капстоун модуля.
 *
 * ENTITY Product07 + ProductRepository07 (готовы ниже).
 *
 * ЗАДАНИЕ — напишите @DataJpaTest-класс с @Autowired TestEntityManager em и ProductRepository07 repo:
 *
 *   1) crud_roundtrip():
 *        Product07 saved = repo.save(new Product07("Кофе", 100));
 *        assertThat(repo.findById(saved.getId())).isPresent();
 *        repo.deleteById(saved.getId());
 *        assertThat(repo.findById(saved.getId())).isEmpty();
 *
 *   2) cold_read():
 *        Product07 p = em.persistAndFlush(new Product07("Чай", 50));
 *        em.clear();
 *        assertThat(repo.findById(p.getId()).orElseThrow().getName()).isEqualTo("Чай");
 *
 *   3) derived_query():
 *        em.persist(new Product07("Кофе арабика", 100)); em.persist(new Product07("Чай", 50)); em.flush();
 *        assertThat(repo.findByNameContainingIgnoreCase("кофе")).hasSize(1);
 *
 *   4) custom_query():
 *        em.persist(new Product07("Дешёвый", 10)); em.persist(new Product07("Дорогой", 1000)); em.flush();
 *        assertThat(repo.findExpensive(100)).extracting(Product07::getName).containsExactly("Дорогой");
 *
 *   5) pagination():
 *        for (int i = 0; i < 5; i++) em.persist(new Product07("P" + i, i)); em.flush();
 *        var page = repo.findAll(PageRequest.of(0, 2));
 *        assertThat(page.getTotalElements()).isEqualTo(5);
 *        assertThat(page.getContent()).hasSize(2);
 *
 * ОЖИДАЕМЫЙ ИТОГ: репозиторий полностью покрыт быстрыми срезовыми тестами на встроенной H2.
 *
 * ПОДСКАЗКА: соедините приёмы задач 01 (CRUD), 03 (clear), 04 (derived), 05 (@Query).
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    protected Product07() {}
    Product07(String name, int price) { this.name = name; this.price = price; }
    Long getId() { return id; }
    String getName() { return name; }
}
