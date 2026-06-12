package m82_spring_data_jpa_lazy_loading.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// Runner (каркас)
// ============================================================
// TODO: @Component
class CatalogRunner07 implements CommandLineRunner {
    private final CategoryRepository07 repo;
    private final CatalogService07 service;
    CatalogRunner07(CategoryRepository07 repo, CatalogService07 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        for (String name : new String[]{"Электроника", "Книги", "Одежда"}) {
            Category07 c = new Category07(name);
            c.addProduct(new Product07(name + "-1"));
            c.addProduct(new Product07(name + "-2"));
            repo.save(c);
        }
        repo.save(new Category07("Распродажа"));   // пустая → overview покажет 0
        // TODO: service.listWithProducts();
        // TODO: service.search("ни");     // "Книги"
        // TODO: service.overview();       // 4 строки, последняя с 0
    }
}
