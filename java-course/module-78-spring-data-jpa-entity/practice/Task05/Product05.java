import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @Embedded
    private Money05 price;

    protected Product05() {}
    public Product05(String name, Money05 price) { this.name = name; this.price = price; }
}
