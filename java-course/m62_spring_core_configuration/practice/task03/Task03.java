package m62_spring_core_configuration.practice.task03;

/**
 * Задача 03 — Модуль 62: @Profile — разные бины для dev и prod
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Одно приложение — два источника данных в зависимости от окружения:
 *   1) Создайте интерфейс DataSource с методом getUrl().
 *   2) Создайте два класса-реализации:
 *        - DevDataSource  — @Profile("dev"),  возвращает "jdbc:h2:mem:testdb"
 *        - ProdDataSource — @Profile("prod"), возвращает "jdbc:postgresql://prod-host:5432/appdb"
 *   3) В конфигурации объявите оба бина через @Bean + @Profile.
 *   4) В main() активируйте профиль "dev" программно и убедитесь, что поднимается DevDataSource.
 *      Затем попробуйте профиль "prod" — убедитесь, что поднимается ProdDataSource.
 *
 * АКТИВАЦИЯ ПРОФИЛЯ (три варианта):
 *   // Вариант А — программно (до refresh):
 *   AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
 *   ctx.getEnvironment().setActiveProfiles("dev");
 *   ctx.register(DataSourceConfig.class);
 *   ctx.refresh();
 *
 *   // Вариант Б — JVM-флаг при запуске:
 *   // -Dspring.profiles.active=prod
 *
 *   // Вариант В — переменная среды:
 *   // SPRING_PROFILES_ACTIVE=prod
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ (профиль dev):
 *   Активный DataSource: jdbc:h2:mem:testdb
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ (профиль prod):
 *   Активный DataSource: jdbc:postgresql://prod-host:5432/appdb
 *
 * ПОДСКАЗКА:
 *   @Bean
 *   @Profile("dev")
 *   public DataSource devDataSource() { return new DevDataSource(); }
 *
 * ПРИМЕР ФАЙЛОВ СВОЙСТВ:
 *   src/main/resources/application-dev.properties
 *     db.url=jdbc:h2:mem:testdb
 *
 *   src/main/resources/application-prod.properties
 *     db.url=jdbc:postgresql://prod-host:5432/appdb
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Task03 {

    public static void main(String[] args) {
        // --- Демонстрация для профиля dev ---
        System.out.println("=== Профиль DEV ===");
        AnnotationConfigApplicationContext ctxDev = createContextWithProfile("dev");
        DataSource dsDev = ctxDev.getBean(DataSource.class);
        System.out.println("Активный DataSource: " + dsDev.getUrl());
        ctxDev.close();

        // --- Демонстрация для профиля prod ---
        System.out.println("\n=== Профиль PROD ===");
        AnnotationConfigApplicationContext ctxProd = createContextWithProfile("prod");
        DataSource dsProd = ctxProd.getBean(DataSource.class);
        System.out.println("Активный DataSource: " + dsProd.getUrl());
        ctxProd.close();
    }

    private static AnnotationConfigApplicationContext createContextWithProfile(String profile) {
        // Создаём контекст
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // Активируем профиль
        context.getEnvironment().setActiveProfiles(profile);

        // Загружаем properties из файла в Environment
        loadPropertiesFromFile(context.getEnvironment(), profile);

        // Регистрируем конфигурацию
        context.register(DataSourceConfig.class);

        // Обновляем контекст (создаём бины)
        context.refresh();

        return context;
    }

    private static void loadPropertiesFromFile(ConfigurableEnvironment environment, String profile) {
        // Путь к файлу свойств
        String path = "java-course/m62_spring_core_configuration/practice/task03/resources/application-" + profile + ".properties";

        Properties props = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл свойств: " + path, e);
        }

        // Создаём PropertySource и добавляем его в Environment
        PropertySource<?> propertySource = new PropertiesPropertySource("custom-properties", props);
        environment.getPropertySources().addFirst(propertySource);
    }
}