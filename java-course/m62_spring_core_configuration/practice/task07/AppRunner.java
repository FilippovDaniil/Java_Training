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
@Component
public class AppRunner {

    @Autowired
    private NotificationService service;

    @Autowired
    private Environment env;

    @Value("file:java-course/m62_spring_core_configuration/practice/task07/resources/banner.txt")
    private Resource banner;

    @Value("#{${app.max-retries:3} * 1000}")
    private long retryDelayMs;

    public void run() throws IOException {
        // Читаем баннер
        String bannerText = new String(banner.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("=== BANNER ===\n" + bannerText);

        // Активные профили и app.name
        String[] activeProfiles = env.getActiveProfiles();
        System.out.println("Активные профили: " + String.join(", ", activeProfiles));
        String appName = env.getProperty("app.name", "Неизвестно");
        System.out.println("app.name = " + appName);

        // retryDelayMs из SpEL
        System.out.println("retryDelayMs = " + retryDelayMs + " мс");

        // Отправка уведомления
        String message = "Сервис " + appName + " запущен";
        service.send("admin@example.com", message);
    }
}