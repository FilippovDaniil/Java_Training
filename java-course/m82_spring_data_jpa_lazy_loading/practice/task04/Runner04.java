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

@Component
class Runner04 implements CommandLineRunner {
    private final CategoryRepository04 repo;
    private final ReportService04 service;
    Runner04(CategoryRepository04 repo, ReportService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category04 c = new Category04(name);
            c.addProduct(new Product04(name + "-1"));
            c.addProduct(new Product04(name + "-2"));
            repo.save(c);
        }
        // TODO: service.reportFetch();
    }
}
