package m87_hibernate_deep_dive_querying.practice.task04;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
class Product04 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product04() {}
    public Product04(String name, int price) { this.name = name; this.price = price; }


}
