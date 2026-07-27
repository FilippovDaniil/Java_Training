package m77_spring_data_jpa_intro.practice.task05;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

// TODO: interface CategoryRepository05 extends JpaRepository<Category05, Long> {}

@Component
class CategorySeed05 implements CommandLineRunner {

    // TODO: внедрите CategoryRepository05
    private final CategoryRepository05 repo;

    @Autowired
    public CategorySeed05(CategoryRepository05 repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        // TODO: сохраните "Электроника", "Книги", "Одежда"
        repo.saveAll(List.of(
                new Category05("Электроника"),
                new Category05("Книги"),
                new Category05("Одежда")
        ));
        // TODO: выведите "Категорий: " + repo.count()
        System.out.println("Категорий: " + repo.count());
    }
}
