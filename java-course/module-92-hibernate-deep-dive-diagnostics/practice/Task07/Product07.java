import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category07 category;
    protected Product07() {}
    public Product07(String name, int price) { this.name = name; this.price = price; }
    public void setCategory(Category07 c) { this.category = c; }
}
