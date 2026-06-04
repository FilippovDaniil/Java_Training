import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

interface ProductRepository03 extends JpaRepository<Product03, Long> {
    List<Product03> findByPriceGreaterThan(int price);
    List<Product03> findByCategoryOrderByPriceAsc(String category);
    long countByCategory(String category);
}
