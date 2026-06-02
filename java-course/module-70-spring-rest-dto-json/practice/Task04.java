/**
 * Задача 04 — Модуль 70: Маппинг сущность ↔ DTO
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ДАНО: «внутренняя сущность» TaskEntity04 с полями, которые НЕЛЬЗЯ показывать наружу
 *       (internalNote). Нужно отдавать клиенту только публичные поля.
 *
 * ЗАДАНИЕ:
 *   1) TaskResponse04 — record(Long id, String title, String status) — публичный DTO.
 *   2) TaskMapper04.toResponse(TaskEntity04 e) — статический метод маппинга:
 *      перенести id, title, status; internalNote НЕ переносить.
 *   3) В контроллере getOne возьмите готовую сущность из getStubEntity()
 *      и верните результат маппинга (НЕ саму сущность!).
 *
 * ЦЕЛЬ: на практике отделить внутреннюю модель от внешнего контракта.
 *
 * ПОДСКАЗКА: маппер — это просто перекладывание нужных полей в DTO.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

// «Внутренняя сущность» (как будто из БД) — содержит служебное поле
class TaskEntity04 {
    Long id = 1L;
    String title = "Купить кофе";
    String status = "NEW";
    String internalNote = "СЛУЖЕБНОЕ — наружу нельзя!";
}

// TODO: record TaskResponse04(Long id, String title, String status)

class TaskMapper04 {
    // TODO: static TaskResponse04 toResponse(TaskEntity04 e) { ... }
}

@RestController
@RequestMapping("/api/tasks")
class MappingController04 {

    private TaskEntity04 getStubEntity() {
        return new TaskEntity04();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable Long id) {
        // TODO: верните TaskMapper04.toResponse(getStubEntity())
        return null;
    }
}
