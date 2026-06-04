import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product03() {}
    public Product03(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
