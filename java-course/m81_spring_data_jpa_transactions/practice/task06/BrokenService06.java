package m81_spring_data_jpa_transactions.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// --- АНТИ-ПАТТЕРН: self-invocation (для изучения) ---
@Service
class BrokenService06 {
    private final ProductRepository06 repo;
    BrokenService06(ProductRepository06 repo) { this.repo = repo; }

    public void outer(String name) {
        this.inner(name);   // ❌ @Transactional на inner() НЕ сработает (самовызов)
    }

    @Transactional
    public void inner(String name) {
        repo.save(new Product06(name));
    }
}
