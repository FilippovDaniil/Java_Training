package m90_hibernate_deep_dive_locking.practice.task01;

import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    @Version
    private long version;
    protected Product01() {}
    public Product01(String name, int stock) { this.name = name; this.stock = stock; }
    public Long getId() { return id; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
