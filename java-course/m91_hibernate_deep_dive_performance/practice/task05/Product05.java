package m91_hibernate_deep_dive_performance.practice.task05;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p5_seq")
    @SequenceGenerator(name = "p5_seq", sequenceName = "p5_seq", allocationSize = 100)
    private Long id;
    private String name;
    protected Product05() {}
    public Product05(String name) { this.name = name; }
}
