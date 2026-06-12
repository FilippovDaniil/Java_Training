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

@Component
class Runner05 implements CommandLineRunner {
    private final CategoryRepository05 repo;
    private final ReportService05 service;
    Runner05(CategoryRepository05 repo, ReportService05 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category05 c = new Category05(name);
            c.addProduct(new Product05(name + "-1"));
            c.addProduct(new Product05(name + "-2"));
            repo.save(c);
        }
        // TODO: service.reportGraph("");   // пустая подстрока подходит всем — один JOIN-запрос
    }
}
