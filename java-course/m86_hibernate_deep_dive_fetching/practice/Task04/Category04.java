package m86_hibernate_deep_dive_fetching.practice.task04;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity @Table(name = "categories")
class Category04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product04> products = new ArrayList<>();
    protected Category04() {}
    public Category04(String name) { this.name = name; }
    public Long getId() { return id; }
    public List<Product04> getProducts() { return products; }
    public void addProduct(Product04 p) { products.add(p); p.setCategory(this); }
}
