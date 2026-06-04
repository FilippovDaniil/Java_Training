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

// --- ТЕСТ (каркас) ---
// TODO: @DataJpaTest
class ProductRepositorySuite07 {

    // TODO: @Autowired TestEntityManager em;
    // TODO: @Autowired ProductRepository07 repo;

    @Test
    void crud_roundtrip() {
        // TODO: Product07 saved = repo.save(new Product07("Кофе", 100));
        // TODO: assertThat(repo.findById(saved.getId())).isPresent();
        // TODO: repo.deleteById(saved.getId());
        // TODO: assertThat(repo.findById(saved.getId())).isEmpty();
    }

    @Test
    void cold_read() {
        // TODO: Product07 p = em.persistAndFlush(new Product07("Чай", 50));
        // TODO: em.clear();
        // TODO: assertThat(repo.findById(p.getId()).orElseThrow().getName()).isEqualTo("Чай");
    }

    @Test
    void derived_query() {
        // TODO: em.persist(new Product07("Кофе арабика", 100));
        // TODO: em.persist(new Product07("Чай", 50));
        // TODO: em.flush();
        // TODO: assertThat(repo.findByNameContainingIgnoreCase("кофе")).hasSize(1);
    }

    @Test
    void custom_query() {
        // TODO: em.persist(new Product07("Дешёвый", 10));
        // TODO: em.persist(new Product07("Дорогой", 1000));
        // TODO: em.flush();
        // TODO: assertThat(repo.findExpensive(100)).extracting(Product07::getName).containsExactly("Дорогой");
    }

    @Test
    void pagination() {
        // TODO: for (int i = 0; i < 5; i++) em.persist(new Product07("P" + i, i));
        // TODO: em.flush();
        // TODO: var page = repo.findAll(PageRequest.of(0, 2));
        // TODO: assertThat(page.getTotalElements()).isEqualTo(5);
        // TODO: assertThat(page.getContent()).hasSize(2);
    }
}
