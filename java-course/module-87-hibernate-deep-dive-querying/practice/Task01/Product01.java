import jakarta.persistence.*;
import java.util.List;

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private int price;
    protected Product01() {}
    public Product01(String name, String category, int price) {
        this.name = name; this.category = category; this.price = price;
    }
    public String getName() { return name; }
    public int getPrice() { return price; }
}
