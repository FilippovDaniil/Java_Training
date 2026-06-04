/**
 * Задача 05 — Модуль 84: конфликт версий — ObjectOptimisticLockingFailureException
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Смоделируйте «потерянное обновление»: два пользователя прочитали товар одной версии,
 *   первый сохранил (версия выросла), второй сохраняет устаревшую копию → конфликт.
 *
 *   1) Product05 уже содержит @Version и setVersion (setVersion — ТОЛЬКО для имитации
 *      устаревшей клиентской копии в этом учебном примере; в реальном коде версию не трогают).
 *   2) staleUpdate(): @Transactional
 *        a) p1 = repo.findById(id) → version V; p1.setPrice(...); repo.save(p1) (версия → V+1);
 *        b) соберите detached-копию p2 с тем же id и СТАРОЙ версией V (имитируем второго
 *           пользователя, читавшего раньше), p2.setPrice(другая цена);
 *        c) repo.save(p2) → должно бросить ObjectOptimisticLockingFailureException.
 *   3) В Runner поймайте исключение и напечатайте "Конфликт версий: " + e.getClass().getSimpleName().
 *
 * ЦЕЛЬ: убедиться, что @Version защищает от затирания чужих изменений.
 *
 * ПОДСКАЗКА: detached-копия — это new Product05(...) с выставленными id и version
 *            (как будто пришла от клиента). save() для неё = merge → UPDATE с WHERE version=V.
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
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@Entity @Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @Version
    private long version;

    protected Product05() {}
    public Product05(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public long getVersion() { return version; }
    public void setVersion(long version) { this.version = version; } // только для имитации stale-копии
}

interface ProductRepository05 extends JpaRepository<Product05, Long> {}

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

@Component
class Runner05 implements CommandLineRunner {
    private final ProductRepository05 repo;
    private final ProductService05 service;
    Runner05(ProductRepository05 repo, ProductService05 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Long id = repo.save(new Product05("Монитор", 15000)).getId();
        try {
            service.staleUpdate(id);
            System.out.println("Конфликт НЕ возник (неожиданно)");
        } catch (Exception e) {
            // TODO: System.out.println("Конфликт версий: " + e.getClass().getSimpleName());
        }
    }
}
