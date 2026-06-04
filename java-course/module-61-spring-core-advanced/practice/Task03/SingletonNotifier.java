import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// ============================================================
// Singleton: один экземпляр на весь контекст
// ============================================================

// TODO: добавьте @Component
// TODO: добавьте @Scope("singleton")   (можно не указывать — это умолчание)
class SingletonNotifier {
    public void send(String message) {
        System.out.println("[SINGLETON:" + System.identityHashCode(this) + "] " + message);
    }
}
