package m86_hibernate_deep_dive_fetching.practice.task02;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product02> products = new ArrayList<>();
    protected Category02() {}
    public Category02(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product02> getProducts() { return products; }
    public void addProduct(Product02 p) { products.add(p); p.setCategory(this); }
}
