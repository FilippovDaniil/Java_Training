package m85_hibernate_deep_dive_lifecycle.practice.task04;

import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product04() {}
    public Product04(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
}
