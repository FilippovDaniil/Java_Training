package m89_hibernate_deep_dive_inheritance.practice.task05;

import jakarta.persistence.*;

@Entity @Table(name = "wide_static")
class WideStatic05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String description;
    protected WideStatic05() {}
    public WideStatic05(String name, int price, int stock, String description) {
        this.name = name; this.price = price; this.stock = stock; this.description = description;
    }
    public Long getId() { return id; }
    public void setPrice(int price) { this.price = price; }
}
