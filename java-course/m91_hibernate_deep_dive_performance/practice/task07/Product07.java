package m91_hibernate_deep_dive_performance.practice.task07;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.List;

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p7_seq")
    @SequenceGenerator(name = "p7_seq", sequenceName = "p7_seq", allocationSize = 50)
    private Long id;
    private String name;
    private String category;
    private int price;
    protected Product07() {}
    public Product07(String name, String category, int price) {
        this.name = name; this.category = category; this.price = price;
    }
    public String getName() { return name; }
    public int getPrice() { return price; }
}
