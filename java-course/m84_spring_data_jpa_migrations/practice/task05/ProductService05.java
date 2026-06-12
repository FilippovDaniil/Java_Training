package m84_spring_data_jpa_migrations.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ProductService05 {
    private final ProductRepository05 repo;
    ProductService05(ProductRepository05 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void staleUpdate(Long id) {
        // TODO: Product05 p1 = repo.findById(id).orElseThrow();
        // TODO: long oldVersion = p1.getVersion();
        // TODO: p1.setPrice(100); repo.saveAndFlush(p1);   // версия → oldVersion + 1

        // TODO: имитируем второго пользователя со старой версией:
        // TODO: Product05 stale = new Product05(p1.getName(), 200);
        // TODO: stale.setId(id); stale.setVersion(oldVersion);
        // TODO: repo.saveAndFlush(stale);   // WHERE version = oldVersion → 0 строк → исключение
    }
}
