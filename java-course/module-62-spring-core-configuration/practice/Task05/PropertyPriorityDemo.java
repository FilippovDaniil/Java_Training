import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// ============================================================
// Компонент для демонстрации приоритета
// ============================================================

// TODO: @Component
class PropertyPriorityDemo {

    // TODO: @Value("${app.name}")  — должно быть "OverriddenApp"
    private String appName;

    // TODO: @Value("${app.env}")   — должно быть "override"
    private String appEnv;

    // TODO: @Value("${app.debug}") — должно быть "false" (только в default)
    private String debug;

    public void printProperties() {
        // TODO: вывести все три поля
        // Объясните в комментарии, почему override.properties победил над default.properties:
        // Подсказка: Spring регистрирует PropertySource-ы в порядке объявления;
        //            при поиске ключа опрашивает с конца (последний зарегистрированный = первый найденный).
    }
}
