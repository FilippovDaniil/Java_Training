import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category07 category;
    protected Product07() {}
    public Product07(String name) { this.name = name; }
    public void setCategory(Category07 c) { this.category = c; }
}
