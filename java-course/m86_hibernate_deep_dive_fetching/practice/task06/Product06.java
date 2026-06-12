package m86_hibernate_deep_dive_fetching.practice.task06;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category06 category;
    protected Product06() {}
    public Product06(String name) { this.name = name; }
    public void setCategory(Category06 c) { this.category = c; }
}
