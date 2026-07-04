package m62_spring_core_configuration.practice.task02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// ============================================================
// Компонент, читающий свойства через Environment
// ============================================================

// TODO: @Component
@Component
class ConfigReader {

    // TODO: внедрите Environment (@Autowired или через конструктор)
    private Environment env;

    public ConfigReader(Environment env) {
        this.env = env;
    }

    public void printConfig() {
        // TODO: прочитайте app.name, app.port (Integer, дефолт 9090), app.debug (дефолт "false")
        // TODO: выведите активные профили через env.getActiveProfiles()
        String name = env.getProperty("app.name");
        int port = env.getProperty("app.port", Integer.class, 9090);
        boolean debug = env.getProperty("app.debug", Boolean.class, false);
        System.out.println("Name: " + name + "\nPort: " + port + "\nDebug: " + debug);
    }
}
