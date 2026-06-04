import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne @JoinColumn(name = "category_id")   // владелец связи (FK)
    private Category05 category;
    protected Product05() {}
    public Product05(String name) { this.name = name; }
    public Long getId() { return id; }
    public Category05 getCategory() { return category; }
    public void setCategory(Category05 c) { this.category = c; }
}
