import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

interface ProductRepository01 extends JpaRepository<Product01, Long> {

    // TODO: @Query("SELECT p FROM Product01 p WHERE p.price > :min AND p.category = :cat")
    List<Product01> expensiveInCategory(/* @Param("min") */ long min, /* @Param("cat") */ String category);

    // TODO: @Query("SELECT p FROM Product01 p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :part, '%'))")
    List<Product01> searchByName(/* @Param("part") */ String part);
}
