package m78_spring_data_jpa_entity.practice.task02;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @Column(name = "product_name", nullable = false, length = 100)
    private String name;

    // TODO: @Column(nullable = false, unique = true, length = 32)
    private String sku;

    // TODO: @Column(length = 1000)
    private String description;

    protected Product02() {}
    public Product02(String name, String sku) { this.name = name; this.sku = sku; }
}
