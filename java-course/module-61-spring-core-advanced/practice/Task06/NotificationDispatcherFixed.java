import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class NotificationDispatcherFixed {

    private final RoutingServiceFixed rs;

    // TODO: добавьте @Lazy к параметру rs, чтобы разорвать цикл
    NotificationDispatcherFixed(RoutingServiceFixed rs) {
        this.rs = rs;
    }

    public void dispatch(String message) {
        System.out.println("Dispatching: " + message);
        rs.route(message);
    }
}
