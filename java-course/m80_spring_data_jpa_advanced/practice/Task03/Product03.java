package m80_spring_data_jpa_advanced.practice.task03;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private String category;
    private String secretNote;   // не хотим тянуть наружу
    protected Product03() {}
    public Product03(String name, long price, String category) {
        this.name = name; this.price = price; this.category = category; this.secretNote = "служебное";
    }
}
