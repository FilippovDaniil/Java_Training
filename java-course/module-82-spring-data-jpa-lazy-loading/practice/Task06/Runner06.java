import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Component
class Runner06 implements CommandLineRunner {
    private final CategoryRepository06 repo;
    private final ReportService06 service;
    Runner06(CategoryRepository06 repo, ReportService06 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Category06 electronics = new Category06("Электроника");
        electronics.addProduct(new Product06("Ноутбук"));
        electronics.addProduct(new Product06("Телефон"));
        repo.save(electronics);
        repo.save(new Category06("Пустая категория"));   // count должен быть 0
        // TODO: service.report();
    }
}
