package m59_spring_core_intro.practice.task04;

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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // TODO 2: получить ProductService и PriceService
        ProductService productService = ctx.getBean(ProductService.class);
        PriceService priceService = ctx.getBean(PriceService.class);
        // TODO 3: вызвать методы обоих сервисов
        System.out.println(priceService.getPrice("Ноутбук"));
        productService.listAll();
        // TODO 4: сравнить ProductRepository внутри обоих сервисов (через геттер getRepository())
        System.out.println(productService.getRepository() == priceService.getRepository());
        // TODO 5: закрыть контекст
    }
}
