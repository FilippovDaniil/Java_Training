package m79_spring_data_jpa_repository.practice.task05;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
class Runner05 implements CommandLineRunner {

    private final ProductRepository05 repo;

    @Autowired
    Runner05(ProductRepository05 repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product05("A", 100),
                new Product05("B", 5000),
                new Product05("C", 2000),
                new Product05("D", 80000),
                new Product05("E", 300)));
        // TODO: выведите топ-3 дорогих (findTop3ByOrderByPriceDesc)
        System.out.println(repo.findTop3ByOrderByPriceDesc());
        // TODO: выведите самый дешёвый (findFirstByOrderByPriceAsc)
        System.out.println(repo.findFirstByOrderByPriceAsc());
        // TODO: выведите все по убыванию цены: repo.findAll(Sort.by("price").descending())
        System.out.println(repo.findAll(Sort.by("price").descending()));
    }
}
