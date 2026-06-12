package m62_spring_core_configuration.practice.task07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// ============================================================
// Запускающий компонент
// ============================================================

// TODO: @Component
class AppRunner {

    // TODO: @Autowired NotificationService service
    private NotificationService service;

    // TODO: @Autowired Environment env
    private Environment env;

    // TODO: @Value("classpath:banner.txt")
    private Resource banner;

    // TODO: @Value("#{${app.max-retries:3} * 1000}")
    private long retryDelayMs;

    public void run() throws IOException {
        // TODO: 1) прочитать и вывести содержимое banner
        // TODO: 2) вывести активный профиль и app.name из env
        // TODO: 3) вывести retryDelayMs
        // TODO: 4) вызвать service.send("admin@example.com", "Сервис " + env.getProperty("app.name") + " запущен")
    }
}
