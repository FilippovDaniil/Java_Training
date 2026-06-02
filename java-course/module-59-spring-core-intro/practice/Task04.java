/**
 * Задача 04 — Модуль 59: @Bean с зависимостями (DI через Java-конфиг)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   В этой задаче появляется третий сервис — PriceService. Он зависит от
 *   ProductRepository. Нужно добавить его в конфигурацию.
 *
 *   1) В AppConfig добавьте @Bean-метод priceService(), который принимает
 *      ProductRepository как параметр метода (альтернативный способ DI через Java-конфиг):
 *
 *        @Bean
 *        public PriceService priceService(ProductRepository productRepository) {
 *            return new PriceService(productRepository);
 *        }
 *
 *      Spring автоматически найдёт нужный бин по типу параметра.
 *
 *   2) Добавьте @Bean-метод productService(), который вызывает productRepository()
 *      напрямую (уже показан в theory.md):
 *        return new ProductService(productRepository());
 *
 *   3) Поднимите контекст. Получите оба сервиса (ProductService и PriceService).
 *   4) Вызовите productService.listAll() и priceService.getPrice("Ноутбук").
 *   5) Проверьте: productService и priceService используют один и тот же
 *      экземпляр ProductRepository (выведите ==).
 *
 * ПОДСКАЗКА:
 *   Два равнозначных способа ссылаться на другой бин в @Bean-методе:
 *     а) вызов метода напрямую: new ProductService(productRepository())
 *     б) параметр метода:       public PriceService priceService(ProductRepository repo)
 *   В обоих случаях Spring возвращает один и тот же singleton.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Task04 {

    public static void main(String[] args) {
        // TODO 1: поднять контейнер с AppConfig
        // TODO 2: получить ProductService и PriceService
        // TODO 3: вызвать методы обоих сервисов
        // TODO 4: сравнить ProductRepository внутри обоих сервисов (через геттер getRepository())
        // TODO 5: закрыть контекст
    }
}

// ============================================================
// Конфигурация — дополнить (TODO)
// ============================================================

// TODO: @Configuration
class AppConfig {

    // TODO: @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    // TODO: @Bean — DI через вызов метода: productRepository()
    public ProductService productService() {
        // TODO: return new ProductService(productRepository());
        return null;
    }

    // TODO: @Bean — DI через параметр метода
    public PriceService priceService(ProductRepository productRepository) {
        // TODO: return new PriceService(productRepository);
        return null;
    }
}

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================

interface ProductRepository {
    java.util.List<String> findAll();
    java.util.Map<String, Double> getPriceMap();
}

class InMemoryProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        return java.util.List.of("Ноутбук", "Мышь", "Клавиатура");
    }

    @Override
    public java.util.Map<String, Double> getPriceMap() {
        return java.util.Map.of("Ноутбук", 75000.0, "Мышь", 1500.0, "Клавиатура", 3500.0);
    }
}

class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductRepository getRepository() { return repository; }

    public void listAll() {
        System.out.println("=== Список товаров ===");
        repository.findAll().forEach(System.out::println);
    }
}

class PriceService {
    private final ProductRepository repository;

    public PriceService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductRepository getRepository() { return repository; }

    public double getPrice(String name) {
        return repository.getPriceMap().getOrDefault(name, -1.0);
    }
}
