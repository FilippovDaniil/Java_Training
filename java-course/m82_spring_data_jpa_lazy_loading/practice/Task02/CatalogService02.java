package m82_spring_data_jpa_lazy_loading.practice.task02;

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
class CatalogService02 {
    private final CategoryRepository02 repo;
    CatalogService02(CategoryRepository02 repo) { this.repo = repo; }

    // НЕТ @Transactional — сессия закроется внутри findById, доступ к products упадёт
    public void brokenRead(Long id) {
        Category02 c = repo.findById(id).orElseThrow();
        // TODO: try { int n = c.getProducts().size(); ... } catch (Exception e) {
        // TODO:   System.out.println("Поймано: " + e.getClass().getSimpleName()); }
    }

    // TODO: @Transactional
    public int fixedRead(Long id) {
        Category02 c = repo.findById(id).orElseThrow();
        // TODO: return c.getProducts().size();   // внутри транзакции — ОК
        return -1;
    }
}
