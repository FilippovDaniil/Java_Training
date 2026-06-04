/**
 * Задача 07 — Модуль 107: МИНИ-ПРОЕКТ «Тест-сьют связей и конкурентности»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, com.h2database:h2, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: покрыть @DataJpaTest'ом «опасную зону» JPA: каскад, lazy/detach, JOIN FETCH, @Version.
 *       Капстоун модуля.
 *
 * ENTITY: Category07 1—N Product07 (cascade=ALL, orphanRemoval, LAZY), Product07 с @Version (готовы).
 *
 * ЗАДАНИЕ — @DataJpaTest-класс с @Autowired TestEntityManager em, CategoryRepository07 catRepo,
 *           ProductRepository07 prodRepo:
 *
 *   1) cascade_persist():
 *        Category07 c = new Category07("Напитки"); c.addProduct(new Product07("Кофе",100));
 *        c.addProduct(new Product07("Чай",50));
 *        em.persistAndFlush(c); em.clear();
 *        assertThat(prodRepo.count()).isEqualTo(2);
 *
 *   2) lazy_after_detach_fails():
 *        Long id = em.persistAndFlush(categoryWith2()).getId(); em.clear();
 *        Category07 loaded = catRepo.findById(id).orElseThrow(); em.detach(loaded);
 *        assertThatThrownBy(() -> loaded.getProducts().size())
 *               .isInstanceOf(LazyInitializationException.class);
 *
 *   3) join_fetch_ok():
 *        Long id = em.persistAndFlush(categoryWith2()).getId(); em.clear();
 *        Category07 loaded = catRepo.findByIdWithProducts(id).orElseThrow(); em.detach(loaded);
 *        assertThat(Hibernate.isInitialized(loaded.getProducts())).isTrue();
 *
 *   4) optimistic_lock():
 *        Long id = em.persistAndFlush(new Product07("X",10)).getId(); em.clear();
 *        Product07 a = prodRepo.findById(id).orElseThrow();
 *        Product07 b = prodRepo.findById(id).orElseThrow();
 *        a.setPrice(20); prodRepo.saveAndFlush(a);
 *        b.setPrice(30);
 *        assertThatThrownBy(() -> prodRepo.saveAndFlush(b))
 *               .isInstanceOf(OptimisticLockingFailureException.class);
 *
 * ОЖИДАЕМЫЙ ИТОГ: ключевые эффекты связей и конкурентности зафиксированы тестами.
 *
 * ПОДСКАЗКА: соедините задачи 02 (detach), 03 (fetch), 04 (cascade), 05 (version).
 */

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Category07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product07> products = new ArrayList<>();

    protected Category07() {}
    Category07(String name) { this.name = name; }
    void addProduct(Product07 p) { products.add(p); p.setCategory(this); }
    Long getId() { return id; }
    List<Product07> getProducts() { return products; }
}
