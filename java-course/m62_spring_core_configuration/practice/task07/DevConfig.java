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
// TODO: @Profile("dev")
// TODO: @PropertySource("classpath:app-dev.properties")
@Configuration
@Profile("dev")
@PropertySource("file:java-course/m62_spring_core_configuration/practice/task07/resources/app-dev.properties")
class DevConfig {

    public DevConfig() {
        System.out.println("=== DevConfig загружен ===");
    }

    // TODO: @Bean
    @Bean
    public NotificationService notificationService() {
        // TODO: вернуть new ConsoleNotificationService()
        System.out.println("Создаём ConsoleNotificationService");
        return new ConsoleNotificationService();
    }
}
