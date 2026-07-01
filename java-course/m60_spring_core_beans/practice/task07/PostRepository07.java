package m60_spring_core_beans.practice.task07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// PostRepository — нижний слой (ни от кого не зависит)
// ============================================================

// TODO: @Repository
@Repository
class PostRepository07 {
    private long idCounter = 0;

    public long save(String title) {
        idCounter++;
        System.out.println("[PostRepository] Сохранён пост #" + idCounter + ": " + title);
        return idCounter;
    }
}
