/**
 * Задача 01 — Модуль 60: Первый @Component и @ComponentScan
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Пометьте класс ArticleFormatter аннотацией @Component.
 *   2) Создайте класс конфигурации AppConfig, добавьте на него:
 *        @Configuration
 *        @ComponentScan(basePackages = "blog")
 *   3) В методе main создайте ApplicationContext:
 *        AnnotationConfigApplicationContext ctx =
 *            new AnnotationConfigApplicationContext(AppConfig.class);
 *   4) Получите бин из контекста:
 *        ArticleFormatter formatter = ctx.getBean(ArticleFormatter.class);
 *   5) Вызовите formatter.format("Spring бины") и выведите результат.
 *   6) Закройте контекст: ctx.close().
 *
 * ПОДСКАЗКА:
 *   Имя бина по умолчанию — имя класса с маленькой буквы ("articleFormatter").
 *   ctx.getBean("articleFormatter") и ctx.getBean(ArticleFormatter.class) — оба варианта работают.
 */
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Task01 {

    public static void main(String[] args) {
        // TODO: создайте AnnotationConfigApplicationContext с AppConfig.class
        // TODO: получите бин ArticleFormatter
        // TODO: вызовите format("Spring бины") и выведите результат
        // TODO: закройте контекст
    }
}

// ============================================================
// Конфигурация — добавьте нужные аннотации (TODO-метки)
// ============================================================

// TODO: @Configuration
// TODO: @ComponentScan(basePackages = "blog")
class AppConfig {
    // Тело пустое — Spring сканирует пакет и сам регистрирует бины
}

// ============================================================
// Компонент — пометьте аннотацией @Component
// ============================================================

// TODO: @Component
class ArticleFormatter {

    /**
     * Форматирует заголовок статьи.
     * Готовая реализация — менять не нужно.
     */
    public String format(String title) {
        return "=== " + title.toUpperCase() + " ===";
    }
}
