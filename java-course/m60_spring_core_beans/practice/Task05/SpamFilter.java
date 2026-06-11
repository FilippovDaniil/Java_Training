package m60_spring_core_beans.practice.task05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Спам-фильтр — готов
// ============================================================

@Component
class SpamFilter {
    /**
     * Возвращает false, если текст содержит слово "диплом" (антиспам-заглушка).
     */
    public boolean check(String text) {
        return !text.toLowerCase().contains("диплом");
    }
}
