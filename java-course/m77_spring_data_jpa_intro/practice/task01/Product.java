package m77_spring_data_jpa_intro.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: @Entity + @Table(name = "products")
class Product {

    // TODO: @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private long price;

    protected Product() {}   // конструктор без аргументов для JPA

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public long getPrice() { return price; }
}
