package m91_hibernate_deep_dive_performance.practice.task03;

import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private int price;
    protected Product03() {}
    public Product03(String name, String category, int price) {
        this.name = name; this.category = category; this.price = price;
    }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
