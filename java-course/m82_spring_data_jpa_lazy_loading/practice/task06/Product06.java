package m82_spring_data_jpa_lazy_loading.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category06 category;
    protected Product06() {}
    public Product06(String name) { this.name = name; }
    public void setCategory(Category06 c) { this.category = c; }
}
