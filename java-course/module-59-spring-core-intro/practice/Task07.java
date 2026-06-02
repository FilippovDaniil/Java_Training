/**
 * Задача 07 — Модуль 59: МИНИ-ПРОЕКТ — Сервисный слой магазина через Java-конфиг
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Соберите полноценный сервисный слой небольшого магазина с помощью Spring-контейнера.
 *
 *   Граф зависимостей:
 *
 *       ApplicationContext
 *              │
 *       ┌──────▼──────────────────────────┐
 *       │         AppConfig               │
 *       │  @Bean productRepository()      │
 *       │  @Bean productService()    ─────┼──► ProductService(ProductRepository)
 *       │  @Bean orderService()      ─────┼──► OrderService(ProductService)
 *       └─────────────────────────────────┘
 *
 *   Шаги:
 *   1) Реализуйте AppConfig (@Configuration):
 *        - @Bean productRepository() → InMemoryProductRepository
 *        - @Bean productService()    → ProductService(productRepository())
 *        - @Bean orderService()      → OrderService(productService())
 *      Все зависимости — через вызов соответствующего @Bean-метода.
 *
 *   2) Поднимите контейнер: new AnnotationConfigApplicationContext(AppConfig.class)
 *
 *   3) Выполните сценарий через OrderService:
 *        a) orderService.placeOrder("Ноутбук", 2)  — оформить заказ
 *        b) orderService.placeOrder("Мышь", 5)
 *        c) orderService.printOrders()             — вывести все заказы
 *
 *   4) Дополнительно: убедитесь, что ProductService внутри OrderService и
 *      напрямую полученный из контекста — один и тот же объект (синглтон).
 *
 *   5) Закройте контекст.
 *
 * ПОДСКАЗКА:
 *   @Configuration
 *   class AppConfig {
 *       @Bean public ProductRepository productRepository() { … }
 *       @Bean public ProductService productService() {
 *           return new ProductService(productRepository());
 *       }
 *       @Bean public OrderService orderService() {
 *           return new OrderService(productService());
 *       }
 *   }
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

public class Task07 {

    public static void main(String[] args) {
        // TODO 1: поднять контейнер с AppConfig
        // TODO 2: получить OrderService из контекста
        // TODO 3: выполнить сценарий:
        //         orderService.placeOrder("Ноутбук", 2)
        //         orderService.placeOrder("Мышь", 5)
        //         orderService.printOrders()
        // TODO 4: получить ProductService напрямую и сравнить с тем, что внутри OrderService (==)
        // TODO 5: закрыть контекст
    }
}

// ============================================================
// Конфигурация — заполнить (TODO)
// ============================================================

// TODO: @Configuration
class AppConfig {

    // TODO: @Bean
    public ProductRepository productRepository() {
        // TODO: return new InMemoryProductRepository();
        return null;
    }

    // TODO: @Bean
    public ProductService productService() {
        // TODO: return new ProductService(productRepository());
        return null;
    }

    // TODO: @Bean
    public OrderService orderService() {
        // TODO: return new OrderService(productService());
        return null;
    }
}

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================

/** Базовый интерфейс: список товаров и проверка наличия. */
interface ProductRepository {
    List<String> findAll();
    boolean exists(String name);
}

class InMemoryProductRepository implements ProductRepository {
    private final List<String> catalog = List.of(
            "Ноутбук", "Мышь", "Клавиатура", "Монитор", "Наушники"
    );

    @Override
    public List<String> findAll() {
        return catalog;
    }

    @Override
    public boolean exists(String name) {
        return catalog.contains(name);
    }
}

/** Сервис товаров: знает каталог, делегирует репозиторию. */
class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public boolean isAvailable(String name) {
        return repository.exists(name);
    }

    public void printCatalog() {
        System.out.println("--- Каталог товаров ---");
        repository.findAll().forEach(p -> System.out.println("  • " + p));
    }
}

/** Строка заказа: товар + количество. */
class OrderLine {
    final String product;
    final int quantity;

    OrderLine(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("  %-15s x%d", product, quantity);
    }
}

/** Сервис заказов: зависит от ProductService — проверяет наличие перед заказом. */
class OrderService {
    private final ProductService productService;
    private final List<OrderLine> orders = new ArrayList<>();

    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    /** Геттер нужен для проверки синглтона в задании. */
    public ProductService getProductService() {
        return productService;
    }

    public void placeOrder(String product, int quantity) {
        if (!productService.isAvailable(product)) {
            System.out.println("Товар не найден в каталоге: " + product);
            return;
        }
        orders.add(new OrderLine(product, quantity));
        System.out.printf("Заказ оформлен: %s x%d%n", product, quantity);
    }

    public void printOrders() {
        System.out.println("=== Все заказы (" + orders.size() + ") ===");
        orders.forEach(System.out::println);
    }
}
