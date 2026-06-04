import jakarta.persistence.*;
import java.util.List;

@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product04() {}
    public Product04(String name, int price) { this.name = name; this.price = price; }
    public String getName() { return name; }
    public int getPrice() { return price; }
}
