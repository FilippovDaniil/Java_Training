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

interface ProductRepository07 extends JpaRepository<Product07, Long>,
                                      JpaSpecificationExecutor<Product07> {

    // TODO: @Query JPQL поиск по подстроке без регистра
    List<Product07> searchByKeyword(/* @Param("kw") */ String kw);

    // TODO: @Query DTO-проекция: SELECT new ProductCard07(p.name, p.price, p.category) FROM Product07 p
    List<ProductCard07> cards();

    // TODO: @Modifying @Query UPDATE цен категории на factor
    int discount(/* @Param("factor") */ double factor, /* @Param("cat") */ String category);
}
