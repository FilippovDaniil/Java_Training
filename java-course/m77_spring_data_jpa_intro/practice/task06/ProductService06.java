package m77_spring_data_jpa_intro.practice.task06;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// TODO: @Service
@Service
class ProductService06 {

    // TODO: внедрите ProductRepository06 через конструктор
    private final ProductRepository06 repo;

    @Autowired
    public ProductService06(ProductRepository06 repo) {
        this.repo = repo;
    }

    public Product06 addProduct(String name, long price) {
        // TODO: return repo.save(new Product06(name, price));
        return repo.save(new Product06(name, price));
    }

    public List<Product06> findExpensive(long min) {
        // TODO: repo.findAll().stream().filter(p -> p.getPrice() >= min).toList()
        return repo.findAll().stream().filter(p -> p.getPrice() >= min).toList();
    }

    public long total() {
        // TODO: return repo.count();
        return repo.count();
    }
}
