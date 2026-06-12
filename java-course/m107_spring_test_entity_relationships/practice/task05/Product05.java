package m107_spring_test_entity_relationships.practice.task05;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @Version
    private long version;

    protected Product05() {}
    Product05(String name, int price) { this.name = name; this.price = price; }
    Long getId() { return id; }
    void setPrice(int price) { this.price = price; }
}
