package m81_spring_data_jpa_transactions.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// --- ПРАВИЛЬНО: inner вынесен в отдельный бин ---
// TODO: @Service
class InnerService06 {
    private final ProductRepository06 repo;
    InnerService06(ProductRepository06 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void inner(String name) {
        repo.save(new Product06(name));
    }
}
