import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories")
class Category01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @OneToMany(mappedBy = "category")
    private List<Product01> products = new ArrayList<>();

    protected Category01() {}
    public Category01(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Product01> getProducts() { return products; }
}
