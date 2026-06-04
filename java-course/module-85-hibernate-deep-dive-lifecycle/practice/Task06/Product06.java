import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product06() {}
    public Product06(String name, int price) { this.name = name; this.price = price; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
