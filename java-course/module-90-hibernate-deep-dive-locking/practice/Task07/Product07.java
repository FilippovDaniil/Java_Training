import jakarta.persistence.*;
import java.util.List;

@Entity @Table(name = "products")
// TODO: @SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ? AND version = ?")
// TODO: @SQLRestriction("deleted = false")
// TODO: @Audited
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    @Version
    private long version;
    private boolean deleted = false;
    protected Product07() {}
    public Product07(String name, int stock) { this.name = name; this.stock = stock; }
    public Long getId() { return id; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
