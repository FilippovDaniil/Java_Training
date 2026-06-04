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

@Service
class ReportService03 {
    private final CategoryRepository03 repo;
    ReportService03(CategoryRepository03 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void reportNaive() {
        // TODO: List<Category03> all = repo.findAll();   // 1 запрос
        // TODO: for (Category03 c : all)                 // +1 запрос на products КАЖДОЙ категории
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size() + " товаров");
        // TODO: посчитайте SELECT'ы в логе — их 1 + N
    }
}
