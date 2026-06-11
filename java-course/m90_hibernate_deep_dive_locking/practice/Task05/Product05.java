package m90_hibernate_deep_dive_locking.practice.task05;

import jakarta.persistence.*;

@Entity @Table(name = "products")
// TODO: @SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ?")
// TODO: @SQLRestriction("deleted = false")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean deleted = false;
    protected Product05() {}
    public Product05(String name) { this.name = name; }
    public Long getId() { return id; }
}
