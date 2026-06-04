import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// ============================================================
// Компонент с разными формами @Value
// ============================================================

// TODO: @Component
class SpelDemo {

    // TODO: @Value("${app.timeout:30}") — значение по умолчанию
    private int timeout;

    // TODO: @Value("#{2 * 1024}") — SpEL-арифметика
    private int bufferSize;

    // TODO: @Value("#{systemProperties['user.name']}") — системное свойство
    private String osUser;

    // TODO: @Value("#{environment['app.name'] ?: 'Unnamed'}") — Elvis-оператор
    private String appNameOrDefault;

    // TODO: @Value("#{${app.timeout:30} * 1000}") — смешанный: ${} внутри #{}
    private long timeoutMs;

    public void print() {
        // TODO: вывести все поля с пояснениями
    }
}
