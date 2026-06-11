package m107_spring_test_entity_relationships.practice.task07;

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

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class RelationshipsSuite07 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired CategoryRepository07 catRepo;
    // TODO: @Autowired ProductRepository07 prodRepo;

    @Test
    void cascade_persist() {
        // TODO: Category07 c = new Category07("Напитки");
        // TODO: c.addProduct(new Product07("Кофе", 100)); c.addProduct(new Product07("Чай", 50));
        // TODO: em.persistAndFlush(c); em.clear();
        // TODO: assertThat(prodRepo.count()).isEqualTo(2);
    }

    @Test
    void lazy_after_detach_fails() {
        // TODO: Long id = em.persistAndFlush(categoryWith2()).getId(); em.clear();
        // TODO: Category07 loaded = catRepo.findById(id).orElseThrow(); em.detach(loaded);
        // TODO: assertThatThrownBy(() -> loaded.getProducts().size())
        //              .isInstanceOf(LazyInitializationException.class);
    }

    @Test
    void join_fetch_ok() {
        // TODO: Long id = em.persistAndFlush(categoryWith2()).getId(); em.clear();
        // TODO: Category07 loaded = catRepo.findByIdWithProducts(id).orElseThrow(); em.detach(loaded);
        // TODO: assertThat(Hibernate.isInitialized(loaded.getProducts())).isTrue();
    }

    @Test
    void optimistic_lock() {
        // TODO: Long id = em.persistAndFlush(new Product07("X", 10)).getId(); em.clear();
        // TODO: Product07 a = prodRepo.findById(id).orElseThrow();
        // TODO: Product07 b = prodRepo.findById(id).orElseThrow();
        // TODO: a.setPrice(20); prodRepo.saveAndFlush(a);
        // TODO: b.setPrice(30);
        // TODO: assertThatThrownBy(() -> prodRepo.saveAndFlush(b))
        //              .isInstanceOf(OptimisticLockingFailureException.class);
    }

    private Category07 categoryWith2() {
        Category07 c = new Category07("Напитки");
        c.addProduct(new Product07("Кофе", 100));
        c.addProduct(new Product07("Чай", 50));
        return c;
    }
}
