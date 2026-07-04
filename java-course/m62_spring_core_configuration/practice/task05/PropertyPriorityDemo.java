package m62_spring_core_configuration.practice.task05;

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
@Component
class PropertyPriorityDemo {

    // TODO: @Value("${app.name}")  — должно быть "OverriddenApp"
    @Value("${app.name}")
    private String appName;

    // TODO: @Value("${app.env}")   — должно быть "override"
    @Value("${app.env}")
    private String appEnv;

    // TODO: @Value("${app.debug}") — должно быть "false" (только в default)
    @Value("${app.debug}")
    private String debug;

    public void printProperties() {
        // TODO: вывести все три поля
        // Объясните в комментарии, почему override.properties победил над default.properties:
        // Подсказка: Spring регистрирует PropertySource-ы в порядке объявления;
        //            при поиске ключа опрашивает с конца (последний зарегистрированный = первый найденный).
        System.out.println("AppName: " + appName);
        System.out.println("AppEnv: " + appEnv);
        System.out.println("AppDebug: " + debug);
    }
}
