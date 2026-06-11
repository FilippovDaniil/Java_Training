package m77_spring_data_jpa_intro.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// TODO: @Entity @Table(name = "products")
class Product07 {
    // TODO: @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    protected Product07() {}
    public Product07(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
}
