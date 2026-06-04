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
class ConfigReader {

    // TODO: внедрите Environment (@Autowired или через конструктор)
    private Environment env;

    public void printConfig() {
        // TODO: прочитайте app.name, app.port (Integer, дефолт 9090), app.debug (дефолт "false")
        // TODO: выведите активные профили через env.getActiveProfiles()
    }
}
