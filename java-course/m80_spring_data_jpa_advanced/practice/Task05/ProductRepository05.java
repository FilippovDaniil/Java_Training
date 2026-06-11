package m80_spring_data_jpa_advanced.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

interface ProductRepository05 extends JpaRepository<Product05, Long> {

    // TODO: @Modifying
    // TODO: @Query("UPDATE Product05 p SET p.price = p.price * :factor WHERE p.category = :cat")
    int raisePrices(/* @Param("factor") */ double factor, /* @Param("cat") */ String category);
}
