import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity @Table(name = "categories")
class Category07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product07> products = new ArrayList<>();
    protected Category07() {}
    public Category07(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Product07> getProducts() { return products; }
    public void addProduct(Product07 p) { products.add(p); p.setCategory(this); }
}
