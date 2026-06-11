package m87_hibernate_deep_dive_querying.practice.task03;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
    public String getName() { return name; }
    public int getPrice() { return price; }
}
