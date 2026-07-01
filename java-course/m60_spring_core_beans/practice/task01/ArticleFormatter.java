package m60_spring_core_beans.practice.task01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// ============================================================
// Компонент — пометьте аннотацией @Component
// ============================================================

// TODO: @Component
@Component
class ArticleFormatter {

    /**
     * Форматирует заголовок статьи.
     * Готовая реализация — менять не нужно.
     */
    public String format(String title) {
        return "=== " + title.toUpperCase() + " ===";
    }
}
