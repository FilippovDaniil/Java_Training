package m62_spring_core_configuration.practice.task02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// ============================================================
// Конфигурация
// ============================================================

// TODO: @Configuration, @PropertySource("classpath:app.properties"), @ComponentScan
@Configuration
@ComponentScan(basePackages = "m62_spring_core_configuration.practice.task02")
@PropertySource("file:java-course/m62_spring_core_configuration/practice/task02/resources/application.properties")
class AppConfig2 {
}
