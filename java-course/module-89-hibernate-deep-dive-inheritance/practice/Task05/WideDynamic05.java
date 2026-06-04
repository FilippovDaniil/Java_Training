import jakarta.persistence.*;

@Entity @Table(name = "wide_dynamic")
// TODO: @DynamicUpdate
class WideDynamic05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String description;
    protected WideDynamic05() {}
    public WideDynamic05(String name, int price, int stock, String description) {
        this.name = name; this.price = price; this.stock = stock; this.description = description;
    }
    public Long getId() { return id; }
    public void setPrice(int price) { this.price = price; }
}
