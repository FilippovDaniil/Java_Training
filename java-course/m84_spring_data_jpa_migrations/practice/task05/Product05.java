package m84_spring_data_jpa_migrations.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @Version
    private long version;

    protected Product05() {}
    public Product05(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public long getVersion() { return version; }
    public void setVersion(long version) { this.version = version; } // только для имитации stale-копии
}
