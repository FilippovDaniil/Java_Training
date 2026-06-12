package m77_spring_data_jpa_intro.practice.task03;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
class CrudRunner03 implements CommandLineRunner {
    private final ProductRepository03 repo;
    CrudRunner03(ProductRepository03 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        // TODO: CREATE — Product03 saved = repo.save(new Product03("Кофе", 500)); Long id = saved.getId();
        // TODO: READ   — repo.findById(id).ifPresent(p -> System.out.println("Найден: " + p.getName()));
        // TODO: UPDATE — найдите, setPrice(550), repo.save(...)
        // TODO: DELETE — repo.deleteById(id); System.out.println("Существует: " + repo.existsById(id));
    }
}
