package m78_spring_data_jpa_entity.practice.task07;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

// TODO: interface ProductRepository07 extends JpaRepository<Product07, Long> {}

// TODO: @Component
@Component
class SeedRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductRepository07

    private final ProductRepository07 repo;

    @Autowired
    public SeedRunner07(ProductRepository07 repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        // TODO: сохраните 2 товара (например, "Ноутбук"/"SKU-1" и "Мышь"/"SKU-2")
        Money07 money = new Money07(777L,"Hustle");
        Product07 ari = new Product07("Tamara","Ckimanauri",money,ProductStatus07.ACTIVE);
        Product07 tamara = repo.save(ari);
        Long tamara_id = tamara.getId();
        // TODO: выведите count и getDisplayLabel() каждого
        System.out.println(repo.count());
        repo.findById(tamara_id).ifPresent(p -> System.out.println("Tamara the best: " + p.getDisplayLabel()));
    }
}
