package m59_spring_core_intro.practice.task06;

/**
 * Задача 06 — Модуль 59: Именованные бины, несколько бинов одного типа, синглтон
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   В реальном проекте может быть несколько реализаций одного интерфейса.
 *   Здесь два репозитория: «в памяти» и «файловый».
 *
 *   1) В AppConfig объявите два @Bean-метода с явными именами:
 *        @Bean("inMemoryRepo")  → возвращает InMemoryProductRepository
 *        @Bean("fileRepo")      → возвращает FileProductRepository
 *
 *   2) Объявите productService, который использует "inMemoryRepo":
 *        @Bean
 *        public ProductService productService(@Qualifier("inMemoryRepo") ProductRepository repo)
 *      (или через вызов inMemoryProductRepository())
 *
 *   3) В main():
 *        a) Получите все бины типа ProductRepository через:
 *             Map<String, ProductRepository> all =
 *                 ctx.getBeansOfType(ProductRepository.class);
 *           Выведите имена (ключи карты).
 *        b) Получите "inMemoryRepo" и "fileRepo" по имени; вызовите findAll() у каждого.
 *        c) Получите productService дважды и убедитесь, что это один объект (синглтон).
 *
 * ПОДСКАЗКА:
 *   @Bean("inMemoryRepo")
 *   public ProductRepository inMemoryProductRepository() {
 *       return new InMemoryProductRepository();
 *   }
 *
 *   ctx.getBeansOfType(ProductRepository.class) возвращает Map<String, ProductRepository>
 *   где ключ — имя бина.
 */

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

public class Task06 {

    public static void main(String[] args) {
        // TODO 1: поднять контейнер с AppConfig
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // TODO 2: ctx.getBeansOfType(ProductRepository.class) — вывести имена бинов
        Map<String, ProductRepository> all = ctx.getBeansOfType(ProductRepository.class);
        // TODO 3: получить "inMemoryRepo" и "fileRepo" по имени, вызвать findAll()
        ProductService productService = ctx.getBean("BeanServiceFile", ProductService.class);
        ProductService productService1 = ctx.getBean("productService", ProductService.class);
        productService.listAll();
        productService1.listAll();
        // TODO 4: получить productService дважды и проверить (==)
        for (Map.Entry<String, ProductRepository> entry : all.entrySet()) {
            String beanName = entry.getKey();
            ProductRepository repo = entry.getValue();
            System.out.println("Бин: " + beanName + " → " + repo.getClass().getSimpleName());
        }
        System.out.println(all.get("inMemoryRepo") == all.get("fileRepo"));
        System.out.println(productService == productService1);

        // TODO 5: закрыть контекст
    }
}
