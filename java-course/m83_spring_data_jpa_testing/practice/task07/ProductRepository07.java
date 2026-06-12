package m83_spring_data_jpa_testing.practice.task07;

import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

interface ProductRepository07 extends JpaRepository<Product07, Long> {
    List<Product07> findByCategory(String category);
    List<Product07> findByPriceLessThanEqual(int price);

    @Query("select count(p) from Product07 p where p.price < :maxPrice")
    long countCheap(@Param("maxPrice") int maxPrice);
}
