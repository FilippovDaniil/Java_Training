package m85_hibernate_deep_dive_lifecycle.practice.task07;

import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product07() {}
    public Product07(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
