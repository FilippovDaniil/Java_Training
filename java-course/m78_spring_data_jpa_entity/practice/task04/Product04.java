package m78_spring_data_jpa_entity.practice.task04;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;

    // TODO: @Transient
    private String displayLabel;

    protected Product04() {}
    public Product04(String name, long price) { this.name = name; this.price = price; }

    public String getDisplayLabel() {
        // TODO: верните name + " — " + price + " руб."
        return null;
    }
}
