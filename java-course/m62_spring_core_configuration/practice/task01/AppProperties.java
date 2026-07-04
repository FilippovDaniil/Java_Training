package m62_spring_core_configuration.practice.task01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// ============================================================
// Компонент с внедрёнными свойствами
// ============================================================

// TODO: пометьте @Component, чтобы Spring создал бин
@Component
class AppProperties {

    // TODO: добавьте @Value("${app.name}")
    @Value("${app.name}")
    private String appName;

    // TODO: добавьте @Value("${app.port}")
    @Value("${app.port}")
    private int port;

    // TODO: добавьте @Value("${app.version}")
    @Value("${app.version}")
    private String version;

    // TODO: добавьте геттеры


    public String getAppName() {
        return appName;
    }

    public int getPort() {
        return port;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Приложение: " + appName + ", порт: " + port + ", версия: " + version;
    }
}
