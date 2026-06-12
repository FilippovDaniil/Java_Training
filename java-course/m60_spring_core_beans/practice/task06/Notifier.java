package m60_spring_core_beans.practice.task06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// ============================================================
// Интерфейс — готов
// ============================================================
interface Notifier {
    void notify(String message);
}
