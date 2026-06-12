package m92_hibernate_deep_dive_diagnostics.practice.task04;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category04 category;
    protected Product04() {}
    public Product04(String name) { this.name = name; }
    public void setCategory(Category04 c) { this.category = c; }
}
