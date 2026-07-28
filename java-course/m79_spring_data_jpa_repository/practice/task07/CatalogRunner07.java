package m79_spring_data_jpa_repository.practice.task07;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.IntStream;

// TODO: @Component
@Component
class CatalogRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductRepository07 (для сидинга) и CatalogService07

    private final ProductRepository07 repo;
    private final CatalogService07 service;

    @Autowired
    public CatalogRunner07(ProductRepository07 repo, CatalogService07 service) {
        this.repo = repo;
        this.service = service;
    }

    @Override
    public void run(String... args) {
        // TODO: засейте ~8 товаров и вызовите методы сервиса, выводя результаты
        List<Product07> items = IntStream.rangeClosed(1, 7)
                .mapToObj(i -> new Product07("Товар " + i, i * 1000L, "Электроника", true))
                .toList();
        repo.saveAll(items);

        System.out.println(service.firstPage(1));
        System.out.println(service.inStock("Электроника"));
        System.out.println(service.priceRange(1550,2400));
        System.out.println(service.search("Товар"));
        System.out.println(service.topExpensive());

    }
}
