package m79_spring_data_jpa_repository.practice.task03;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
class QueryRunner03 implements CommandLineRunner {

    private final ProductRepository03 repo;

    @Autowired
    QueryRunner03(ProductRepository03 repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product03("Ноутбук Lenovo", 80000, "Электроника"),
                new Product03("Мышь", 900, "Электроника"),
                new Product03("Клавиатура", 2500, "Электроника"),
                new Product03("Java book", 1500, "Книги")));
        // TODO: проверьте все четыре метода и выведите результаты
        System.out.println(repo.findByCategoryAndPriceGreaterThan("Электроника",2000));
        System.out.println(repo.findByPriceBetween(1000,2000));
        System.out.println(repo.findByNameContainingIgnoreCase("Java"));
        System.out.println(repo.findByCategoryOrderByPriceDesc("Книги"));
    }
}
