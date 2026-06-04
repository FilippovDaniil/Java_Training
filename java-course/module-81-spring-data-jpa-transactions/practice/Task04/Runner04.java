import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    private final FailService04 service;
    Runner04(ProductRepository04 repo, FailService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        try { service.saveAndFailUnchecked("A"); } catch (Exception e) { System.out.println("unchecked: " + e.getMessage()); }
        try { service.saveAndFailChecked("B"); }   catch (Exception e) { System.out.println("checked: " + e.getMessage()); }
        // TODO: System.out.println("Осталось записей: " + repo.count());
        // Подумайте: сколько записей останется в зависимости от наличия rollbackFor?
    }
}
