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
// Сервис (каркас)
// ============================================================
// TODO: @Service
class CatalogService07 {
    private final CategoryRepository07 repo;
    CatalogService07(CategoryRepository07 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void listWithProducts() {
        // TODO: for (Category07 c : repo.findAllWithProducts())
        // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size());
    }

    // TODO: @Transactional
    public void search(String part) {
        // TODO: for (Category07 c : repo.findByNameContaining(part))
        // TODO:     System.out.println("найдено: " + c.getName() + " (" + c.getProducts().size() + ")");
    }

    public void overview() {
        // TODO: for (CatalogRow07 r : repo.overview())
        // TODO:     System.out.println(r.name() + " -> " + r.productCount());
    }
}
