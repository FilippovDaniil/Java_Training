package m91_hibernate_deep_dive_performance.practice.task02;

import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p2_seq")
    @SequenceGenerator(name = "p2_seq", sequenceName = "p2_seq", allocationSize = 50)
    private Long id;
    private String name;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
}
