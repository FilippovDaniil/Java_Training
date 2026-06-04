import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;

    protected Product03() {}
    public Product03(String name, long price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setPrice(long price) { this.price = price; }
}
