package m88_hibernate_deep_dive_modeling.practice.task05;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @NaturalId(mutable = false)
    @Column(unique = true)
    private String sku;

    private String name;
    protected Product05() {}
    public Product05(String sku, String name) { this.sku = sku; this.name = name; }
    public String getName() { return name; }
    // TODO: equals/hashCode по sku:
    // TODO: @Override public boolean equals(Object o) {
    // TODO:     return o instanceof Product05 p && sku != null && sku.equals(p.sku);
    // TODO: }
    // TODO: @Override public int hashCode() { return sku != null ? sku.hashCode() : 0; }
}
