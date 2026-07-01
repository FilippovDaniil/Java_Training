package m60_spring_core_beans.practice.task06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// ============================================================
// Реализации — добавьте @Component и @Order (TODO-метки)
// ============================================================

// TODO: @Component
// TODO: @Order(1)
@Component
@Order(1)
class EmailNotifier implements Notifier {
    @Override
    public void notify(String message) {
        // TODO: System.out.println("Email: " + message);
        System.out.println("Email: " + message);
    }
}
