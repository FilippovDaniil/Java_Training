package m80_spring_data_jpa_advanced.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    protected Product01() {}
    public Product01(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
    public String getName() { return name; }
}
