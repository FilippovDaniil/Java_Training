import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// ============================================================
// Prototype: новый экземпляр при каждом запросе
// ============================================================

// TODO: добавьте @Component
// TODO: добавьте @Scope("prototype")
class PrototypeNotifier {
    public void send(String message) {
        System.out.println("[PROTOTYPE:" + System.identityHashCode(this) + "] " + message);
    }
}
