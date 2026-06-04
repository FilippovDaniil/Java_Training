import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// «Внутренняя сущность» (как будто из БД) — содержит служебное поле
class TaskEntity04 {
    Long id = 1L;
    String title = "Купить кофе";
    String status = "NEW";
    String internalNote = "СЛУЖЕБНОЕ — наружу нельзя!";
}
