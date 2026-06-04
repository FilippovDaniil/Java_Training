import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// ============================================================
// Eager-бин (создаётся при старте контекста)
// ============================================================

// TODO: добавьте @Component
class EagerNotifier {

    // TODO: добавьте @PostConstruct
    public void init() {
        System.out.println("EagerNotifier создан");
    }

    public void send(String message) {
        System.out.println("[EAGER] " + message);
    }
}
