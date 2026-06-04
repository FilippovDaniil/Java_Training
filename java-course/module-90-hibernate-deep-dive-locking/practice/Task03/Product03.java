import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    protected Product03() {}
    public Product03(String name, int stock) { this.name = name; this.stock = stock; }
    public Long getId() { return id; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
