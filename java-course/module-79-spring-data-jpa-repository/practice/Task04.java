/**
 * Задача 04 — Модуль 79: Производные count / exists / delete
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Добавьте в ProductRepository04 derived-методы агрегации и удаления:
 *     - long countByCategory(String category)
 *     - boolean existsBySku(String sku)
 *     - long deleteByCategory(String category)   // возвращает число удалённых
 *   В сервисе DeleteService04 (@Service) метод purge(category) пометьте @Transactional
 *   и вызовите deleteByCategory (модифицирующий запрос требует транзакции!).
 *   В CommandLineRunner: засейте данные, выведите count/exists, затем purge и снова count.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему deleteBy... нужно вызывать внутри @Transactional?
 *
 * ПОДСКАЗКА: модифицирующие запросы выполняются только в активной транзакции.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

@Entity @Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String sku;
    protected Product04() {}
    public Product04(String name, String category, String sku) {
        this.name = name; this.category = category; this.sku = sku;
    }
}

interface ProductRepository04 extends JpaRepository<Product04, Long> {
    // TODO: long countByCategory(String category);
    // TODO: boolean existsBySku(String sku);
    // TODO: long deleteByCategory(String category);
}

@Service
class DeleteService04 {
    private final ProductRepository04 repo;
    DeleteService04(ProductRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public long purge(String category) {
        // TODO: return repo.deleteByCategory(category);
        return 0;
    }
}

@Component
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    private final DeleteService04 service;
    Runner04(ProductRepository04 repo, DeleteService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        repo.saveAll(List.of(
                new Product04("Мышь", "Электроника", "SKU-1"),
                new Product04("Клавиатура", "Электроника", "SKU-2"),
                new Product04("Java book", "Книги", "SKU-3")));
        // TODO: выведите countByCategory("Электроника") и existsBySku("SKU-1")
        // TODO: вызовите service.purge("Электроника"), затем выведите repo.count()
    }
}
