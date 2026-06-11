package m82_spring_data_jpa_lazy_loading.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
class ReportService05 {
    private final CategoryRepository05 repo;
    ReportService05(CategoryRepository05 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void reportGraph(String part) {
        // TODO: for (Category05 c : repo.findByNameContaining(part))
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size());
    }
}
