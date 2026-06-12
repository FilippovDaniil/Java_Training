package m79_spring_data_jpa_repository.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Entity @Table(name = "products")
class Product07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    private boolean available;
    protected Product07() {}
    public Product07(String name, long price, String category, boolean available) {
        this.name = name; this.price = price; this.category = category; this.available = available;
    }
    public String getName() { return name; }
}
