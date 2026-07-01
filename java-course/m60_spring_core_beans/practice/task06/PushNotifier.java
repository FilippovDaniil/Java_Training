package m60_spring_core_beans.practice.task06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// TODO: @Component
// TODO: @Order(3)
@Component
@Order(3)
class PushNotifier implements Notifier {
    @Override
    public void notify(String message) {
        // TODO: System.out.println("Push: " + message);
        System.out.println("Push: " + message);
    }
}
