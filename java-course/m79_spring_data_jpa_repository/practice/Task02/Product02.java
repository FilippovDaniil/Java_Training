package m79_spring_data_jpa_repository.practice.task02;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    private String sku;
    protected Product02() {}
    public Product02(String name, long price, String category, String sku) {
        this.name = name; this.price = price; this.category = category; this.sku = sku;
    }
    public String getName() { return name; }
}
