/**
 * Задача 06 — Модуль 81: Ловушка self-invocation (@Transactional не сработает)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ (исследование ловушки + исправление):
 *   1) ИЗУЧИТЕ BrokenService06: метод outer() (БЕЗ @Transactional) вызывает inner()
 *      (С @Transactional) через this.inner(). Из-за самовызова прокси обходится —
 *      транзакция inner() НЕ откроется.
 *   2) ИСПРАВЬТЕ: вынесите inner() в ОТДЕЛЬНЫЙ бин InnerService06 (@Service) с
 *      @Transactional, и пусть FixedService06 вызывает его через внедрённую ссылку
 *      (тогда вызов идёт через прокси — транзакция работает).
 *   3) В CommandLineRunner вызовите fixedService.outer(...) и убедитесь, что товар сохранён.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему вызов this.inner() внутри того же класса обходит @Transactional?
 *
 * ПОДСКАЗКА: @Transactional работает через прокси (AOP, модуль 63);
 *            внутренний вызов this.method() не проходит через прокси.
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
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

@Entity @Table(name = "products")
class Product06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Product06() {}
    public Product06(String name) { this.name = name; }
}

interface ProductRepository06 extends JpaRepository<Product06, Long> {}

// --- АНТИ-ПАТТЕРН: self-invocation (для изучения) ---
@Service
class BrokenService06 {
    private final ProductRepository06 repo;
    BrokenService06(ProductRepository06 repo) { this.repo = repo; }

    public void outer(String name) {
        this.inner(name);   // ❌ @Transactional на inner() НЕ сработает (самовызов)
    }

    @Transactional
    public void inner(String name) {
        repo.save(new Product06(name));
    }
}

// --- ПРАВИЛЬНО: inner вынесен в отдельный бин ---
// TODO: @Service
class InnerService06 {
    private final ProductRepository06 repo;
    InnerService06(ProductRepository06 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void inner(String name) {
        repo.save(new Product06(name));
    }
}

// TODO: @Service
class FixedService06 {
    // TODO: внедрите InnerService06
    public void outer(String name) {
        // TODO: inner.inner(name);   // вызов через внедрённый бин → прокси → транзакция работает
    }
}

@Component
class Runner06 implements CommandLineRunner {
    private final ProductRepository06 repo;
    private final FixedService06 fixed;
    Runner06(ProductRepository06 repo, FixedService06 fixed) { this.repo = repo; this.fixed = fixed; }

    @Override
    public void run(String... args) {
        // TODO: fixed.outer("Кофе"); System.out.println("Сохранено товаров: " + repo.count());
    }
}
