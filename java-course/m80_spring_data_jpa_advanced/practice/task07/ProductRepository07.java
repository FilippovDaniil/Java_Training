package m80_spring_data_jpa_advanced.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// ============ REPOSITORY ============
interface ProductRepository07 extends JpaRepository<Product07, Long>,
        JpaSpecificationExecutor<Product07> {

    // JPQL поиск по подстроке без регистра
    @Query("SELECT p FROM Product07 p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :kw, '%'))")
    List<Product07> searchByKeyword(@Param("kw") String kw);

    // DTO-проекция
    @Query("SELECT new m80_spring_data_jpa_advanced.practice.task07.ProductCard07(p.name, p.price, p.category) FROM Product07 p")
    List<ProductCard07> cards();

    // @Modifying UPDATE цен категории на factor
    @Modifying
    @Query("UPDATE Product07 p SET p.price = p.price * :factor WHERE p.category = :category")
    int discount(@Param("factor") double factor, @Param("category") String category);
}
