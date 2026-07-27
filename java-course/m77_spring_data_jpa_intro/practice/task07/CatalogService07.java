package m77_spring_data_jpa_intro.practice.task07;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
class CatalogService07 {

    // TODO: внедрите ProductRepository07 и CategoryRepository07 через конструктор
    private final ProductRepository07 productRepo;
    private final CategoryRepository07 categoryRepo;

    @Autowired
    public CatalogService07(ProductRepository07 productRepo, CategoryRepository07 categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public void seed() {
        // TODO: сохраните категории "Электроника","Книги"
        categoryRepo.save(new Category07("Электроника"));
        categoryRepo.save(new Category07("Книги"));
        // TODO: сохраните товары "Ноутбук"(80000,"Электроника"), "Мышь"(900,"Электроника"), "Java book"(1500,"Книги")
        productRepo.save(new Product07("Ноутбук", 80_000, "Электроника"));
        productRepo.save(new Product07("Мышь", 900, "Электроника"));
        productRepo.save(new Product07("Java book", 1500, "Книги"));
    }

    public long productCount() {
        return productRepo.count();
    }    // TODO: productRepo.count()

    public long categoryCount() {
        return categoryRepo.count();
    }   // TODO: categoryRepo.count()
}
