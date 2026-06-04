import jakarta.persistence.*;

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Product01() {}
    public Product01(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}
