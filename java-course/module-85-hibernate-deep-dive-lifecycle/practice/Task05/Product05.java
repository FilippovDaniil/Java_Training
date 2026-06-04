import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product05() {}
    public Product05(String name, int price) { this.name = name; this.price = price; }
}
