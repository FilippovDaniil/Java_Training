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
    @NaturalId(mutable = false)
    @Column(unique = true)
    private String sku;

    private String name;

    // TODO: @Embedded
    private Money07 price;

    protected Product07() {}
    public Product07(String sku, String name, Money07 price) {
        this.sku = sku; this.name = name; this.price = price;
    }
    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public Money07 getPrice() { return price; }

    // equals/hashCode по бизнес-ключу (sku) — стабилен
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product07 that = (Product07) o;
        return Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "Product07{sku='" + sku + "', name='" + name + "', price=" + price + "}";
    }
}
