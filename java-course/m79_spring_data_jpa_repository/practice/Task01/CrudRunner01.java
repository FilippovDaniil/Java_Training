package m79_spring_data_jpa_repository.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
class CrudRunner01 implements CommandLineRunner {
    private final ProductRepository01 repo;
    CrudRunner01(ProductRepository01 repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        // TODO: repo.saveAll(List.of(new Product01("Кофе",500), new Product01("Чай",300), new Product01("Сахар",100)));
        // TODO: System.out.println("Всего: " + repo.count());
        // TODO: repo.findById(1L).ifPresent(p -> System.out.println("Первый: " + p.getName()));
        // TODO: System.out.println("Есть #1: " + repo.existsById(1L));
        // TODO: repo.deleteById(1L); System.out.println("После удаления: " + repo.count());
    }
}
