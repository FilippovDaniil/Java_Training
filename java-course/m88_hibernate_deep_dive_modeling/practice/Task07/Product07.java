package m88_hibernate_deep_dive_modeling.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @NaturalId(mutable = false)
    @Column(unique = true)
    private String sku;

    private String name;

    // TODO: @Embedded
    private Money07 price;

    protected Product07() {}
    public Product07(String sku, String name, Money07 price) {
        this.sku = sku; this.name = name; this.price = price;
    }
    public String getName() { return name; }
    public Money07 getPrice() { return price; }
    // TODO: equals/hashCode ПО sku
}
