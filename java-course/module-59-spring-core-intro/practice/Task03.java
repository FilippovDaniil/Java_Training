/**
 * Задача 03 — Модуль 59: Получить бин из контекста (getBean)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Поднимите контейнер с готовым AppConfig (конфигурация уже написана).
 *   2) Получите бин ProductService из контекста двумя способами:
 *        a) ctx.getBean(ProductService.class)               — по типу
 *        b) ctx.getBean("productService", ProductService.class) — по имени и типу
 *   3) Убедитесь, что оба вызова возвращают один и тот же объект (==).
 *      Выведите результат проверки: "Один и тот же объект: true/false".
 *   4) Вызовите svc.listAll() и убедитесь, что список выводится.
 *
 * ПОДСКАЗКА:
 *   ProductService svc1 = ctx.getBean(ProductService.class);
 *   ProductService svc2 = ctx.getBean("productService", ProductService.class);
 *   System.out.println(svc1 == svc2); // ожидается true — синглтон
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Task03 {

    public static void main(String[] args) {
        // TODO 1: поднять контейнер с AppConfig
        // TODO 2: получить бин по типу
        // TODO 3: получить бин по имени и типу
        // TODO 4: сравнить ссылки (==) и вывести результат
        // TODO 5: вызвать listAll(), закрыть контекст
    }
}

// ============================================================
// Конфигурация (готова — изучите, не менять)
// ============================================================

@Configuration
class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    @Bean
    public ProductService productService() {
        // Spring перехватит вызов productRepository() и вернёт singleton
        return new ProductService(productRepository());
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
