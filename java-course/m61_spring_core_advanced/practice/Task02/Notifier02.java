package m61_spring_core_advanced.practice.task02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// ============================================================
// Интерфейс (тот же, что в Task01)
// ============================================================
interface Notifier02 {
    void send(String message);
}
