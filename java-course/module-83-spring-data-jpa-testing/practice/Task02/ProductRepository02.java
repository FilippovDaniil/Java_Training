import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

interface ProductRepository02 extends JpaRepository<Product02, Long> {
    List<Product02> findByNameContaining(String part);
}
