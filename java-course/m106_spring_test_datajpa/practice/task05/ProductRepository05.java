package m106_spring_test_datajpa.practice.task05;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

interface ProductRepository05 extends JpaRepository<Product05, Long> {
    @Query("select p from Product05 p where p.price > :min")
    List<Product05> findExpensive(@Param("min") int min);

    @Query("select count(p) from Product05 p where p.price < :max")
    long countCheaperThan(@Param("max") int max);
}
