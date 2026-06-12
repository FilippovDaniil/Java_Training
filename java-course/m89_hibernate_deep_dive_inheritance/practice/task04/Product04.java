package m89_hibernate_deep_dive_inheritance.practice.task04;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "products")
class Product04 extends BaseEntity04 {
    private String name;
    protected Product04() {}
    public Product04(String name) { this.name = name; }
}
