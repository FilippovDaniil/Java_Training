package m77_spring_data_jpa_intro.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// TODO: interface CategoryRepository05 extends JpaRepository<Category05, Long> {}

@Component
class CategorySeed05 implements CommandLineRunner {

    // TODO: внедрите CategoryRepository05

    @Override
    public void run(String... args) {
        // TODO: сохраните "Электроника", "Книги", "Одежда"
        // TODO: выведите "Категорий: " + repo.count()
    }
}
