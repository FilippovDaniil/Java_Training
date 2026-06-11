package m106_spring_test_datajpa.practice.task06;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.jdbc.Sql;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    protected Product06() {}
    Product06(String name, int price) { this.name = name; this.price = price; }
    Long getId() { return id; }
    String getName() { return name; }
}
