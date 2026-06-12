package m77_spring_data_jpa_intro.practice.task02;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;

    protected Product02() {}
    public Product02(String name, long price) { this.name = name; this.price = price; }
    public String getName() { return name; }
}
