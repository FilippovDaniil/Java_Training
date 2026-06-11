package m60_spring_core_beans.practice.task02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// TODO: какую аннотацию ставим на веб-слой? (@Controller)
class PostController {
    public String handle(String request) {
        return "Контроллер обработал: " + request;  // заглушка
    }
}
