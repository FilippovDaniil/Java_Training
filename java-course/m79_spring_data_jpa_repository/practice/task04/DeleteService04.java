package m79_spring_data_jpa_repository.practice.task04;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
class DeleteService04 {
    private final ProductRepository04 repo;
    DeleteService04(ProductRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public long purge(String category) {
        // TODO: return repo.deleteByCategory(category);
        return 0;
    }
}
