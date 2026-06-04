import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity @Table(name = "broken_products")
class BrokenProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected BrokenProduct() {}
    public BrokenProduct(String name) { this.name = name; }
    // ПЛОХО: equals/hashCode по generated id (меняется при persist)
    @Override public boolean equals(Object o) {
        return o instanceof BrokenProduct p && Objects.equals(id, p.id);
    }
    @Override public int hashCode() { return Objects.hashCode(id); }
}
