package m62_spring_core_configuration.practice.task01;

/**
 * Задача 01 — Модуль 62: @PropertySource + @Value — загрузить app.properties
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Создайте файл src/main/resources/app.properties (пример ниже в комментарии).
 *   2) Подключите файл к конфигурации через @PropertySource.
 *   3) Внедрите значения через @Value в поля AppProperties:
 *        - app.name  → поле appName (String)
 *        - app.port  → поле port (int)
 *        - app.version → поле version (String)
 *   4) В main() получите бин AppProperties из контекста и выведите поля.
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   Консоль: Приложение: MyService, порт: 8080, версия: 1.0.0
 *
 * ПОДСКАЗКА:
 *   @Configuration
 *   @PropertySource("classpath:app.properties")
 *   public class AppConfig { ... }
 *
 *   В бине-компоненте:
 *   @Value("${app.name}")
 *   private String appName;
 *
 * ПРИМЕР app.properties:
 *   # src/main/resources/app.properties
 *   app.name=MyService
 *   app.port=8080
 *   app.version=1.0.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

public class Task01 {

    public static void main(String[] args) {
        // TODO: создайте AnnotationConfigApplicationContext с AppConfig.class
        //       получите бин AppProperties и выведите его поля
        // Например:
        // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // AppProperties props = ctx.getBean(AppProperties.class);
        // System.out.println("Приложение: " + props.getAppName() + ...);
        // ctx.close();
    }
}
