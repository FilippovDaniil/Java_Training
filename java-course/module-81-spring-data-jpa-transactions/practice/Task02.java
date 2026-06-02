/**
 * Задача 02 — Модуль 81: Dirty checking (UPDATE без save())
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * НАСТРОЙКА: spring.jpa.show-sql=true (чтобы увидеть автоматический UPDATE).
 *
 * ЗАДАНИЕ:
 *   1) ProductService02.rename(id, newName) пометьте @Transactional.
 *      Внутри: найдите товар через repo.findById(id).orElseThrow(), вызовите setName(newName).
 *      НЕ вызывайте repo.save()! При коммите Hibernate сам сделает UPDATE (dirty checking).
 *   2) В CommandLineRunner: сохраните товар, вызовите rename, затем перечитайте
 *      из репозитория и убедитесь, что имя изменилось. В логах должен быть UPDATE.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему изменение managed-сущности сохраняется БЕЗ явного save()?
 *   Сработает ли это для detached-объекта (полученного вне транзакции)?
 *
 * ПОДСКАЗКА: внутри транзакции объект managed → Hibernate отслеживает изменения.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@Entity @Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Product02() {}
    public Product02(String name) { this.name = name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

interface ProductRepository02 extends JpaRepository<Product02, Long> {}

@Service
class ProductService02 {
    private final ProductRepository02 repo;
    ProductService02(ProductRepository02 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void rename(Long id, String newName) {
        // TODO: Product02 p = repo.findById(id).orElseThrow();
        // TODO: p.setName(newName);   // БЕЗ save() — сработает dirty checking
    }
}

@Component
class Runner02 implements CommandLineRunner {
    private final ProductRepository02 repo;
    private final ProductService02 service;
    Runner02(ProductRepository02 repo, ProductService02 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Product02 p = repo.save(new Product02("Старое имя"));
        // TODO: service.rename(p.getId(), "Новое имя");
        // TODO: System.out.println("Имя после rename: " + repo.findById(p.getId()).orElseThrow().getName());
    }
}
