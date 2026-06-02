/**
 * Задача 04 — Модуль 81: Правила отката (rollbackFor для checked-исключений)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Покажите разницу отката для unchecked и checked исключений.
 *   1) saveAndFailUnchecked(name): @Transactional. Сохранить товар, затем
 *      throw new RuntimeException("сбой") → транзакция ОТКАТИТСЯ (товара не будет).
 *   2) saveAndFailChecked(name): @Transactional. Сохранить товар, затем
 *      throw new Exception("сбой") (checked) → по умолчанию транзакция НЕ откатывается!
 *      Чтобы откатывалась — добавьте @Transactional(rollbackFor = Exception.class).
 *   3) В CommandLineRunner вызовите оба метода (в try/catch), затем выведите repo.count()
 *      и проверьте, какие записи остались.
 *
 * ЦЕЛЬ: понять, что checked-исключение по умолчанию НЕ откатывает транзакцию.
 *
 * ПОДСКАЗКА: RuntimeException → откат; checked Exception → коммит (если нет rollbackFor).
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
    protected Product04() {}
    public Product04(String name) { this.name = name; }
}

interface ProductRepository04 extends JpaRepository<Product04, Long> {}

@Service
class FailService04 {
    private final ProductRepository04 repo;
    FailService04(ProductRepository04 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void saveAndFailUnchecked(String name) {
        repo.save(new Product04(name));
        throw new RuntimeException("сбой (unchecked) — должно откатиться");
    }

    // TODO: @Transactional(rollbackFor = Exception.class)  — иначе checked НЕ откатит
    public void saveAndFailChecked(String name) throws Exception {
        repo.save(new Product04(name));
        throw new Exception("сбой (checked)");
    }
}

@Component
class Runner04 implements CommandLineRunner {
    private final ProductRepository04 repo;
    private final FailService04 service;
    Runner04(ProductRepository04 repo, FailService04 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        try { service.saveAndFailUnchecked("A"); } catch (Exception e) { System.out.println("unchecked: " + e.getMessage()); }
        try { service.saveAndFailChecked("B"); }   catch (Exception e) { System.out.println("checked: " + e.getMessage()); }
        // TODO: System.out.println("Осталось записей: " + repo.count());
        // Подумайте: сколько записей останется в зависимости от наличия rollbackFor?
    }
}
