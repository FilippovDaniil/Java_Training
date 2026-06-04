import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category02 category;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
    public void setCategory(Category02 c) { this.category = c; }
}
