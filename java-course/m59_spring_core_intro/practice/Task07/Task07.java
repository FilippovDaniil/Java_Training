package m59_spring_core_intro.practice.task07;

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
