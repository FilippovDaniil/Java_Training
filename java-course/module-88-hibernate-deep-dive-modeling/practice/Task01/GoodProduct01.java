import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity @Table(name = "good_products")
class GoodProduct01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sku;
    private String name;
    protected GoodProduct01() {}
    public GoodProduct01(String sku, String name) { this.sku = sku; this.name = name; }
    // TODO: equals/hashCode ПО sku (бизнес-ключ, стабилен):
    // TODO: @Override public boolean equals(Object o) {
    // TODO:     return o instanceof GoodProduct01 p && sku != null && sku.equals(p.sku);
    // TODO: }
    // TODO: @Override public int hashCode() { return sku != null ? sku.hashCode() : 0; }
}
