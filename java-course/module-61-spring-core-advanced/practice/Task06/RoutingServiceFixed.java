import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class RoutingServiceFixed {

    private final NotificationDispatcherFixed nd;

    RoutingServiceFixed(NotificationDispatcherFixed nd) {
        this.nd = nd;
    }

    public void route(String message) {
        System.out.println("[ROUTE] " + message);
    }
}
