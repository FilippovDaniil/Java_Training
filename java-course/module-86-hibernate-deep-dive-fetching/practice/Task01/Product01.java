import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category01 category;
    protected Product01() {}
    public Product01(String name) { this.name = name; }
    public void setCategory(Category01 c) { this.category = c; }
}
