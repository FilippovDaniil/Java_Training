package m86_hibernate_deep_dive_fetching.practice.task03;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product03> products = new ArrayList<>();
    protected Category03() {}
    public Category03(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product03> getProducts() { return products; }
    public void addProduct(Product03 p) { products.add(p); p.setCategory(this); }
}
