import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")   // обратная сторона (не владелец)
    private List<Product05> products = new ArrayList<>();
    protected Category05() {}
    public Category05(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product05> getProducts() { return products; }
    /** Хелпер: синхронизирует ОБЕ стороны связи. */
    public void addProduct(Product05 p) {
        products.add(p);
        p.setCategory(this);   // ВЛАДЕЛЕЦ — пишет FK
    }
}
