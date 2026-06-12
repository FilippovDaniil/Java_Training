package m82_spring_data_jpa_lazy_loading.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// Сущности (связи LAZY)
// ============================================================
@Entity @Table(name = "categories")
class Category07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product07> products = new ArrayList<>();
    protected Category07() {}
    public Category07(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product07> getProducts() { return products; }
    public void addProduct(Product07 p) { products.add(p); p.setCategory(this); }
}
