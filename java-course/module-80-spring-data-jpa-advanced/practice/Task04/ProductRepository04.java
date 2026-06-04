import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

interface ProductRepository04 extends JpaRepository<Product04, Long> {

    // TODO: @Query("SELECT new ProductSummary04(p.name, p.price) FROM Product04 p WHERE p.price >= :min")
    List<ProductSummary04> summaries(/* @Param("min") */ long min);
}
