import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// ============================================================
// Конфигурация с несколькими @PropertySource
// ============================================================

// TODO: @Configuration
// TODO: @PropertySource("classpath:default.properties")
// TODO: @PropertySource("classpath:override.properties")   — этот имеет БОЛЕЕ ВЫСОКИЙ приоритет
// TODO: @ComponentScan
class MultiSourceConfig {
    // Пусто — Spring подхватит компоненты через @ComponentScan
}
