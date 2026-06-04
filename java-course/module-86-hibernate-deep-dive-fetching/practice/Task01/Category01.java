import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product01> products = new ArrayList<>();
    protected Category01() {}
    public Category01(String name) { this.name = name; }
    public Long getId() { return id; }
    public List<Product01> getProducts() { return products; }
    public void addProduct(Product01 p) { products.add(p); p.setCategory(this); }
}
