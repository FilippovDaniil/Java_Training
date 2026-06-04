import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

interface ProductRepository04 extends JpaRepository<Product04, Long> {

    @Query("select p from Product04 p where p.price between :lo and :hi")
    List<Product04> findInPriceRange(@Param("lo") int lo, @Param("hi") int hi);

    @Query("select new PriceStat04(p.category, avg(p.price)) from Product04 p group by p.category")
    List<PriceStat04> avgPriceByCategory();
}
