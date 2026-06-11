package m92_hibernate_deep_dive_diagnostics.practice.task06;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product06> products = new ArrayList<>();
    protected Category06() {}
    public Category06(String name) { this.name = name; }
    public List<Product06> getProducts() { return products; }
    public void addProduct(Product06 p) { products.add(p); p.setCategory(this); }
}
