package m83_spring_data_jpa_testing.practice.task02;

import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// --- класс-под-тестом дан готовым ---
@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}
