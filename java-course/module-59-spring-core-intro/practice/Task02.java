/**
 * Задача 02 — Модуль 59: Первый Spring-контекст (@Configuration + AnnotationConfigApplicationContext)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Создайте Java-класс конфигурации AppConfig и пометьте его @Configuration.
 *   2) Объявите в нём два @Bean-метода:
 *        - productRepository() — возвращает new InMemoryProductRepository()
 *        - productService()    — возвращает new ProductService(productRepository())
 *   3) В main() поднимите контейнер:
 *        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
 *   4) Убедитесь, что контекст поднялся без исключений (выведите "Контекст запущен").
 *   5) Закройте контекст (ctx.close() или try-with-resources).
 *
 * ПОДСКАЗКА:
 *   @Configuration
 *   public class AppConfig {
 *       @Bean
 *       public ProductRepository productRepository() { … }
 *   }
 *
 *   AnnotationConfigApplicationContext ctx =
 *       new AnnotationConfigApplicationContext(AppConfig.class);
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Task02 {

    public static void main(String[] args) {
        // TODO 1: поднять контейнер с AppConfig
        // TODO 2: вывести "Контекст запущен"
        // TODO 3: закрыть контекст
    }
}

// ============================================================
// Конфигурация — заполнить (TODO)
// ============================================================

// TODO: @Configuration
class AppConfig {

    // TODO: @Bean
    public ProductRepository productRepository() {
        // TODO: вернуть new InMemoryProductRepository()
        return null;
    }

    // TODO: @Bean
    public ProductService productService() {
        // TODO: вернуть new ProductService(productRepository())
        return null;
    }
}

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================

interface ProductRepository {
    java.util.List<String> findAll();
}

class InMemoryProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        return java.util.List.of("Ноутбук", "Мышь", "Клавиатура");
    }
}

class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void listAll() {
        System.out.println("=== Список товаров ===");
        repository.findAll().forEach(System.out::println);
    }
}
