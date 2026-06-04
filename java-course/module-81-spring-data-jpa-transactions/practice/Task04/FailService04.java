import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class FailService04 {
    private final ProductRepository04 repo;
    FailService04(ProductRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void saveAndFailUnchecked(String name) {
        repo.save(new Product04(name));
        throw new RuntimeException("сбой (unchecked) — должно откатиться");
    }

    // TODO: @Transactional(rollbackFor = Exception.class)  — иначе checked НЕ откатит
    public void saveAndFailChecked(String name) throws Exception {
        repo.save(new Product04(name));
        throw new Exception("сбой (checked)");
    }
}
