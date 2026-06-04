import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product02> products = new ArrayList<>();
    protected Category02() {}
    public Category02(String name) { this.name = name; }
    public Long getId() { return id; }
    public List<Product02> getProducts() { return products; }
    public void addProduct(Product02 p) { products.add(p); p.setCategory(this); }
}
