import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category05 category;
    protected Product05() {}
    public Product05(String name) { this.name = name; }
    public void setCategory(Category05 c) { this.category = c; }
}
