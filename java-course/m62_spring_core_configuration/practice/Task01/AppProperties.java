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
class AppProperties {

    // TODO: добавьте @Value("${app.name}")
    private String appName;

    // TODO: добавьте @Value("${app.port}")
    private int port;

    // TODO: добавьте @Value("${app.version}")
    private String version;

    // TODO: добавьте геттеры

    @Override
    public String toString() {
        return "Приложение: " + appName + ", порт: " + port + ", версия: " + version;
    }
}
