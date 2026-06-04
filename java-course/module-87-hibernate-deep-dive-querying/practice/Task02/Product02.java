import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private int price;
    protected Product02() {}
    public Product02(String name, String category, int price) {
        this.name = name; this.category = category; this.price = price;
    }
    public Long getId() { return id; }
    public int getPrice() { return price; }
}
