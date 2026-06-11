package m92_hibernate_deep_dive_diagnostics.practice.task07;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product07> products = new ArrayList<>();
    protected Category07() {}
    public Category07(String name) { this.name = name; }
    public List<Product07> getProducts() { return products; }
    public void addProduct(Product07 p) { products.add(p); p.setCategory(this); }
}
