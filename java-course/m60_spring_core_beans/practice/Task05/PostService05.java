package m60_spring_core_beans.practice.task05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Сервис — реализуйте сеттерную инъекцию
// ============================================================

@Service
class PostService05 {

    // TODO: объявите поле  private SpamFilter spamFilter  (не final!)

    // TODO: добавьте сеттер с @Autowired(required = false)
    //       public void setSpamFilter(SpamFilter spamFilter) { ... }

    public void publish(String text) {
        // TODO: если spamFilter != null и check(text) == false → IllegalArgumentException
        // TODO: иначе → System.out.println("Опубликовано: " + text)
    }
}
