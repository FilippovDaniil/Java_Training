package m82_spring_data_jpa_lazy_loading.practice.task04;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product04> products = new ArrayList<>();
    protected Category04() {}
    public Category04(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Product04> getProducts() { return products; }
    public void addProduct(Product04 p) { products.add(p); p.setCategory(this); }
}
