import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

interface ProductRepository02 extends JpaRepository<Product02, Long> {

    // TODO: @Query(value = "SELECT * FROM products WHERE price > :min", nativeQuery = true)
    List<Product02> nativeExpensive(/* @Param("min") */ long min);

    // TODO: @Query(value = "SELECT AVG(price) FROM products", nativeQuery = true)
    Double averagePrice();
}
