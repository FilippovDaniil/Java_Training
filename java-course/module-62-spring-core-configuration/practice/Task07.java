/**
 * Задача 07 — Модуль 62: МИНИ-ПРОЕКТ — настраиваемый сервис с профилями
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Соберите вместе все темы модуля: @PropertySource, @Value, @Profile, SpEL, Resource.
 *   Реализуйте простую систему конфигурации для сервиса отправки уведомлений.
 *
 *   СТРУКТУРА ПРИЛОЖЕНИЯ:
 *
 *   AppConfig (главная конфигурация)
 *     ├── @PropertySource("classpath:app-common.properties")
 *     ├── @ComponentScan
 *     └── @Import({DevConfig.class, ProdConfig.class})
 *
 *   DevConfig  (@Profile("dev"))
 *     ├── @PropertySource("classpath:app-dev.properties")
 *     └── @Bean NotificationService → ConsoleNotificationService
 *
 *   ProdConfig  (@Profile("prod"))
 *     ├── @PropertySource("classpath:app-prod.properties")
 *     └── @Bean NotificationService → EmailNotificationService
 *
 *   NotificationService (интерфейс)
 *     └── send(String to, String message)
 *
 *   ConsoleNotificationService  (dev)
 *     └── просто выводит сообщение в System.out
 *
 *   EmailNotificationService  (prod)
 *     ├── @Value("${mail.host}") host
 *     ├── @Value("${mail.port:587}") port
 *     └── имитирует отправку (System.out с SMTP-параметрами)
 *
 *   AppRunner (@Component)
 *     ├── внедряет NotificationService и Resource("classpath:banner.txt")
 *     ├── при старте выводит содержимое banner.txt
 *     └── вызывает service.send("admin@example.com", "Сервис запущен")
 *
 * ФАЙЛЫ РЕСУРСОВ (создайте в src/main/resources/):
 *
 *   app-common.properties:
 *     app.name=NotificationHub
 *     app.version=2.0.0
 *     app.max-retries=3
 *
 *   app-dev.properties:
 *     mail.host=localhost
 *     mail.port=1025
 *     app.env=dev
 *
 *   app-prod.properties:
 *     mail.host=smtp.company.com
 *     mail.port=587
 *     app.env=prod
 *
 *   banner.txt:
 *     === NotificationHub запущен ===
 *
 * ЧТО ПРОВЕРИТЬ:
 *   - Профиль "dev"  → ConsoleNotificationService, banner, app.env=dev
 *   - Профиль "prod" → EmailNotificationService с SMTP-параметрами из app-prod.properties
 *   - SpEL: вычислить дедлайн = app.max-retries * 1000 мс через @Value("#{${app.max-retries:3} * 1000}")
 *   - Environment: вывести все активные профили и app.name через env.getProperty()
 *
 * ПОДСКАЗКА — активация профиля:
 *   AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
 *   ctx.getEnvironment().setActiveProfiles("dev");  // или "prod"
 *   ctx.register(AppConfig.class);
 *   ctx.refresh();
 *   ctx.getBean(AppRunner.class).run();
 *   ctx.close();
 */
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

public class Task07 {

    public static void main(String[] args) {
        // TODO: создайте контекст, активируйте профиль "dev" (затем попробуйте "prod")
        //       получите AppRunner и вызовите run()
    }
}

// ============================================================
// Интерфейс сервиса уведомлений
// ============================================================

interface NotificationService {
    void send(String to, String message);
}

// ============================================================
// Реализации (TODO: расставьте аннотации)
// ============================================================

class ConsoleNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        // TODO: вывести "[CONSOLE] → to: <адрес>, сообщение: <текст>"
    }
}

class EmailNotificationService implements NotificationService {

    // TODO: @Value("${mail.host}")
    private String host;

    // TODO: @Value("${mail.port:587}")
    private int port;

    @Override
    public void send(String to, String message) {
        // TODO: вывести "[EMAIL] SMTP: <host>:<port> → <to>: <message>"
    }
}

// ============================================================
// Конфигурации (TODO: расставьте аннотации)
// ============================================================

// TODO: @Configuration
// TODO: @ComponentScan
// TODO: @Import({DevConfig.class, ProdConfig.class})
// TODO: @PropertySource("classpath:app-common.properties")
class AppConfig {
}

// TODO: @Configuration
// TODO: @Profile("dev")
// TODO: @PropertySource("classpath:app-dev.properties")
class DevConfig {

    // TODO: @Bean
    public NotificationService notificationService() {
        // TODO: вернуть new ConsoleNotificationService()
        return null;
    }
}

// TODO: @Configuration
// TODO: @Profile("prod")
// TODO: @PropertySource("classpath:app-prod.properties")
class ProdConfig {

    // TODO: @Bean
    public NotificationService notificationService() {
        // TODO: создать EmailNotificationService, внедрить @Value-поля
        //       (можно через @Autowired Environment или конструктор с @Value)
        return null;
    }
}

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
