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

// TODO: @Configuration
// TODO: @Profile("prod")
// TODO: @PropertySource("classpath:app-prod.properties")
@Configuration
@Profile("prod")
@PropertySource("file:java-course/m62_spring_core_configuration/practice/task07/resources/app-prod.properties")
class ProdConfig {

    public ProdConfig() {
        System.out.println("=== ProdConfig загружен ===");
    }

    // TODO: @Bean
    @Bean
    public NotificationService notificationService() {
        // TODO: создать EmailNotificationService, внедрить @Value-поля
        //       (можно через @Autowired Environment или конструктор с @Value)
        System.out.println("Создаём ConsoleNotificationService");
        return new EmailNotificationService();
    }
}
