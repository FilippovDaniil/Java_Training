import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component
class EmRunner04 implements CommandLineRunner {
    private final ProductDao04 dao;
    EmRunner04(ProductDao04 dao) { this.dao = dao; }

    @Override
    public void run(String... args) {
        // TODO: Product04 p = new Product04("Кофе"); dao.save(p);
        // TODO: System.out.println("Найден: " + dao.find(p.getId()).getName());
    }
}
