package m59_spring_core_intro.practice.task02;

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
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // TODO 2: вывести "Контекст запущен"
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        productService.listAll();
        // TODO 3: закрыть контекст
    }
}
