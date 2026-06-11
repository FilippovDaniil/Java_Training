package m92_hibernate_deep_dive_diagnostics.practice.task02;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
}
