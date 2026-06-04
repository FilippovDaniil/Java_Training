import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category03 category;
    protected Product03() {}
    public Product03(String name) { this.name = name; }
    public void setCategory(Category03 c) { this.category = c; }
}
