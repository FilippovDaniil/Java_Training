package m53_hibernate_inheritance.practice.task03;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

/**
 * Корневой класс иерархии (TABLE_PER_CLASS)
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class ProductTPC {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) // TABLE — единый счётчик на все подтипы
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    public ProductTPC() {}

    public ProductTPC(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return "ProductTPC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
