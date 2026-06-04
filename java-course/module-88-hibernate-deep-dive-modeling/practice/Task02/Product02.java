import jakarta.persistence.*;
import java.util.Objects;

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // TODO: @Embedded
    private Money02 price;
    protected Product02() {}
    public Product02(String name, Money02 price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public Money02 getPrice() { return price; }
}
