import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// ============================================================
// Lazy-бин (создаётся при первом обращении)
// ============================================================

// TODO: добавьте @Component
// TODO: добавьте @Lazy
class LazyNotifier {

    // TODO: добавьте @PostConstruct
    public void init() {
        System.out.println("LazyNotifier создан");
    }

    public void send(String message) {
        System.out.println("[LAZY] " + message);
    }
}
