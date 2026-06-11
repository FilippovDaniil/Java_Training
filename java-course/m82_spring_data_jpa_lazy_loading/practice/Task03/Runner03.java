package m82_spring_data_jpa_lazy_loading.practice.task03;

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

@Component
class Runner03 implements CommandLineRunner {
    private final CategoryRepository03 repo;
    private final ReportService03 service;
    Runner03(CategoryRepository03 repo, ReportService03 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category03 c = new Category03(name);
            c.addProduct(new Product03(name + "-1"));
            c.addProduct(new Product03(name + "-2"));
            repo.save(c);
        }
        // TODO: service.reportNaive();   // в логе: 1 + 3 = 4 SELECT'а
    }
}
