package m63_spring_core_events_aop.practice.task01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// ============================================================
// Слушатель встроенных событий Spring
// ============================================================

@Component
class StartupListener {

    // TODO: добавьте @EventListener
    public void onRefresh(ContextRefreshedEvent event) {
        // TODO: выведите "Контекст поднят! Приложение готово к работе."
    }

    // TODO: добавьте второй метод — слушатель ContextClosedEvent
    // Выведите "Контекст закрыт."
}
