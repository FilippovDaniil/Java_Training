package m82_spring_data_jpa_lazy_loading.practice.task04;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
class ReportService04 {
    private final CategoryRepository04 repo;
    ReportService04(CategoryRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void reportFetch() {
        // TODO: for (Category04 c : repo.findAllWithProducts())
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size());
        // TODO: в логе — ОДИН SELECT ... JOIN ...
    }
}
