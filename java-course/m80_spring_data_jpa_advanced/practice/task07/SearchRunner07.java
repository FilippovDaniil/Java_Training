package m80_spring_data_jpa_advanced.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// ============ RUNNER ============
@Component
class SearchRunner07 implements CommandLineRunner {

    private final ProductRepository07 repository;
    private final CatalogSearchService07 service;

    public SearchRunner07(ProductRepository07 repository, CatalogSearchService07 service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void run(String... args) {
        // ============ 1. ЗАСЕЙТЕ ~6 ТОВАРОВ ============
        System.out.println("\n" + "=".repeat(60));
        System.out.println("1. СОЗДАНИЕ ТОВАРОВ");
        System.out.println("=".repeat(60));

        repository.saveAll(List.of(
                new Product07("Ноутбук", 1000.0, "Электроника", true),
                new Product07("Мышь", 50.0, "Электроника", true),
                new Product07("Клавиатура", 80.0, "Электроника", false),
                new Product07("Книга по Java", 150.0, "Книги", true),
                new Product07("Книга по Spring", 200.0, "Книги", true),
                new Product07("Наушники", 120.0, "Аудио", true)
        ));

        System.out.println("✅ Создано 6 товаров");

        // ============ 2. ВЫЗОВИТЕ search ============
        System.out.println("\n" + "=".repeat(60));
        System.out.println("2. ПОИСК ПО КЛЮЧЕВОМУ СЛОВУ (search)");
        System.out.println("=".repeat(60));

        String keyword = "книг";
        System.out.println("🔍 Поиск: '" + keyword + "'");
        List<Product07> searchResults = service.search(keyword);
        searchResults.forEach(System.out::println);

        // ============ 3. ВЫЗОВИТЕ cards ============
        System.out.println("\n" + "=".repeat(60));
        System.out.println("3. DTO-ПРОЕКЦИЯ (cards)");
        System.out.println("=".repeat(60));

        List<ProductCard07> cards = service.cards();

        // ✅ ИСПРАВЛЕНО: используем %f для double
        cards.forEach(card ->
                System.out.printf("  %s | %.2f | %s%n", card.name(), card.price(), card.category())
        );

        // ============ 4. ВЫЗОВИТЕ filter ============
        System.out.println("\n" + "=".repeat(60));
        System.out.println("4. ФИЛЬТР С SPECIFICATION (filter)");
        System.out.println("=".repeat(60));

        System.out.println("🔍 Фильтр: category='Электроника', price between 50 and 500");
        List<Product07> filtered = service.filter("Электроника", 50L, 500L);
        filtered.forEach(System.out::println);

        // ============ 5. ВЫЗОВИТЕ applyDiscount ============
        System.out.println("\n" + "=".repeat(60));
        System.out.println("5. ПРИМЕНЕНИЕ СКИДКИ (applyDiscount)");
        System.out.println("=".repeat(60));

        String category = "Электроника";
        double factor = 0.9;
        System.out.println("📉 Скидка 10% для категории: " + category);
        int updated = service.applyDiscount(category, factor);
        System.out.println("✅ Обновлено товаров: " + updated);

        System.out.println("\n📋 Товары после скидки:");
        repository.findAll().forEach(System.out::println);
    }
}
