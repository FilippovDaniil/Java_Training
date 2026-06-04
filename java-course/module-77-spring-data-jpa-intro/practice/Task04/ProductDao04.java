import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
class ProductDao04 {

    // TODO: @PersistenceContext private EntityManager em;

    // TODO: @Transactional
    public void save(Product04 p) {
        // TODO: em.persist(p);
    }

    public Product04 find(Long id) {
        // TODO: return em.find(Product04.class, id);
        return null;
    }
}
