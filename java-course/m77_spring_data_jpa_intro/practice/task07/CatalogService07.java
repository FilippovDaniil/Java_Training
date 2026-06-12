package m77_spring_data_jpa_intro.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Репозитории (каркасы)
// ============================================================

// TODO: interface CategoryRepository07 extends JpaRepository<Category07, Long> {}
// TODO: interface ProductRepository07 extends JpaRepository<Product07, Long> {}

// ============================================================
// Сервис (каркас)
// ============================================================

// TODO: @Service
class CatalogService07 {

    // TODO: внедрите ProductRepository07 и CategoryRepository07 через конструктор

    public void seed() {
        // TODO: сохраните категории "Электроника","Книги"
        // TODO: сохраните товары "Ноутбук"(80000,"Электроника"), "Мышь"(900,"Электроника"), "Java book"(1500,"Книги")
    }

    public long productCount() { return 0; }    // TODO: productRepo.count()
    public long categoryCount() { return 0; }   // TODO: categoryRepo.count()
}
