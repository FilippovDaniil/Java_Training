import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "products")
class Product04 extends BaseEntity04 {
    private String name;
    protected Product04() {}
    public Product04(String name) { this.name = name; }
}
