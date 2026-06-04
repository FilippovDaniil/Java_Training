import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// TODO: @Service
class ProductService06 {

    // TODO: внедрите ProductRepository06 через конструктор

    public Product06 addProduct(String name, long price) {
        // TODO: return repo.save(new Product06(name, price));
        return null;
    }

    public List<Product06> findExpensive(long min) {
        // TODO: repo.findAll().stream().filter(p -> p.getPrice() >= min).toList()
        return null;
    }

    public long total() {
        // TODO: return repo.count();
        return 0;
    }
}
