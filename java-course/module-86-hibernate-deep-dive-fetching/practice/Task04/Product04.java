import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category04 category;
    protected Product04() {}
    public Product04(String name) { this.name = name; }
    public void setCategory(Category04 c) { this.category = c; }
}
