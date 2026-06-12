package m91_hibernate_deep_dive_performance.practice.task01;

import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product01 {
    @Id
    // TODO: @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
    // TODO: @SequenceGenerator(name = "prod_seq", sequenceName = "prod_seq", allocationSize = 50)
    private Long id;
    private String name;
    protected Product01() {}
    public Product01(String name) { this.name = name; }
}
