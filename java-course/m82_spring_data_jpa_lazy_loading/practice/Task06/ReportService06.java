package m82_spring_data_jpa_lazy_loading.practice.task06;

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

@Service
class ReportService06 {
    private final CategoryRepository06 repo;
    ReportService06(CategoryRepository06 repo) { this.repo = repo; }

    public void report() {
        // TODO: for (CategorySummary06 s : repo.summaries())
        // TODO:     System.out.println(s.name() + " -> " + s.productCount());
    }
}
