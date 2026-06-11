package m82_spring_data_jpa_lazy_loading.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @ManyToOne(fetch = FetchType.LAZY)  @JoinColumn(name = "category_id")
    private Category01 category;

    protected Product01() {}
    public Product01(String name, Category01 category) { this.name = name; this.category = category; }
    public Long getId() { return id; }
}
