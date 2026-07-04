package m62_spring_core_configuration.practice.task06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// ============================================================
// Конфигурация
// ============================================================

// TODO: @Configuration, @ComponentScan
@Configuration
@ComponentScan(basePackages = "m62_spring_core_configuration.practice.task06")
@PropertySource("file:java-course/m62_spring_core_configuration/practice/task06/resources/application.properties")
class ResourceConfig {
}
