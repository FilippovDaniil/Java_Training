package m86_hibernate_deep_dive_fetching.practice.task05;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    // TODO: вариант A: @BatchSize(size = 10)
    // TODO: вариант B: @Fetch(FetchMode.SUBSELECT)
    private List<Product05> products = new ArrayList<>();

    protected Category05() {}
    public Category05(String name) { this.name = name; }
    public List<Product05> getProducts() { return products; }
    public void addProduct(Product05 p) { products.add(p); p.setCategory(this); }
}
