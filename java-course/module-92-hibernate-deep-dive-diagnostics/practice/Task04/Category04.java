import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product04> products = new ArrayList<>();
    protected Category04() {}
    public Category04(String name) { this.name = name; }
    public List<Product04> getProducts() { return products; }
    public void addProduct(Product04 p) { products.add(p); p.setCategory(this); }
}
