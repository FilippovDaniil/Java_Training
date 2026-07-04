package m62_spring_core_configuration.practice.task01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

// ============================================================
// Конфигурационный класс — расставьте аннотации (TODO-метки)
// ============================================================

// TODO: добавьте @Configuration
// TODO: добавьте @PropertySource("classpath:app.properties")
// TODO: добавьте @ComponentScan (или объявите AppProperties как @Bean) - m62_spring_core_configuration/practice/task01
@Configuration
@ComponentScan(basePackages = "m62_spring_core_configuration.practice.task01")
@PropertySource("file:java-course/m62_spring_core_configuration/practice/task01/resources/application.properties")
class AppConfig {
    // Можно оставить пустым, если AppProperties помечен @Component
}
