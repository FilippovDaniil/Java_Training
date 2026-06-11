package m78_spring_data_jpa_entity.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
class Product07 {

    // TODO: @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @Column(nullable = false, length = 100)
    private String name;

    // TODO: @Column(nullable = false, unique = true, length = 32)
    private String sku;

    // TODO: @Embedded
    private Money07 price;

    // TODO: @Enumerated(EnumType.STRING)
    private ProductStatus07 status;

    private LocalDateTime createdAt;

    // TODO: @Transient
    private String displayLabel;

    protected Product07() {}
    public Product07(String name, String sku, Money07 price, ProductStatus07 status) {
        this.name = name; this.sku = sku; this.price = price;
        this.status = status; this.createdAt = LocalDateTime.now();
    }

    public String getDisplayLabel() {
        // TODO: верните name + " [" + status + "]"
        return null;
    }
}
