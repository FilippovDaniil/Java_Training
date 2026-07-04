package m62_spring_core_configuration.practice.task04;

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
@Component
class SpelDemo {

    // TODO: @Value("${app.timeout:30}") — значение по умолчанию
    @Value("${app.timeout:30}")
    private int timeout;

    // TODO: @Value("#{2 * 1024}") — SpEL-арифметика
    @Value("#{2 * 1024}")
    private int bufferSize;

    // TODO: @Value("#{systemProperties['user.name']}") — системное свойство
    @Value("#{systemProperties['user.name']}")
    private String osUser;

    // TODO: @Value("#{environment['app.name'] ?: 'Unnamed'}") — Elvis-оператор
    @Value("#{environment['app.name'] ?: 'Unnamed'}")
    private String appNameOrDefault;

    // TODO: @Value("#{${app.timeout:30} * 1000}") — смешанный: ${} внутри #{}
    @Value("#{${app.timeout:30} * 1000}")
    private long timeoutMs;

    public void print() {
        // TODO: вывести все поля с пояснениями
        System.out.println("Timeout: " + timeout);
        System.out.println("BufferSize: " + bufferSize);
        System.out.println("OsUser: " + osUser);
        System.out.println("AppNameOrDefault: " + appNameOrDefault);
        System.out.println("TimeoutMs: " + timeoutMs);
    }
}
