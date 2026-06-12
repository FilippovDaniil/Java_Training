package m60_spring_core_beans.practice.task06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// ============================================================
// Сервис — реализуйте инъекцию List<Notifier>
// ============================================================

@Service
class NotificationService {

    // TODO: объявите поле  private final List<Notifier> notifiers

    // TODO: добавьте конструктор, принимающий List<Notifier>

    public void notifyAll(String message) {
        // TODO: переберите notifiers и вызовите notify(message) у каждого
    }
}
