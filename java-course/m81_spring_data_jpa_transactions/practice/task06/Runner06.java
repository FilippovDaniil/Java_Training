package m81_spring_data_jpa_transactions.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
class Runner06 implements CommandLineRunner {
    private final ProductRepository06 repo;
    private final FixedService06 fixed;
    Runner06(ProductRepository06 repo, FixedService06 fixed) { this.repo = repo; this.fixed = fixed; }

    @Override
    public void run(String... args) {
        // TODO: fixed.outer("Кофе"); System.out.println("Сохранено товаров: " + repo.count());
    }
}
