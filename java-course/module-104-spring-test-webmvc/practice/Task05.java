/**
 * Задача 05 — Модуль 104: MockMvcTester — текучий API на AssertJ
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-test (Spring Boot 3.4+ для MockMvcTester).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Тот же контроллер, но проверки — в стиле AssertJ через MockMvcTester (без throws Exception):
 *     1) @WebMvcTest(TaskController05.class); @Autowired MockMvcTester mvc; @MockBean TaskService05 service.
 *     2) get_ok_fluent():
 *          when(service.find(1L)).thenReturn(new TaskDto05(1L, "Кофе", "NEW"));
 *          assertThat(mvc.get().uri("/api/tasks/1"))
 *                 .hasStatusOk()
 *                 .bodyJson().extractingPath("$.title").isEqualTo("Кофе");
 *     3) missing_is_404():
 *          when(service.find(99L)).thenThrow(new java.util.NoSuchElementException());
 *          assertThat(mvc.get().uri("/api/tasks/99")).hasStatus(404);   // если есть @ControllerAdvice
 *          // (если advice не настроен — этот тест можно опустить/адаптировать)
 *
 * ЦЕЛЬ: познакомиться с современным текучим API MockMvcTester (альтернатива perform/andExpect).
 *
 * ВАЖНО: MockMvcTester НЕ требует throws Exception и читается как AssertJ-цепочка.
 *
 * ПОДСКАЗКА: assertThat(...) здесь — org.assertj.core.api.Assertions для MvcTestResult.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

// --- DTO, сервис, контроллер (готовы) ---
record TaskDto05(Long id, String title, String status) {}

@Service
class TaskService05 {
    public TaskDto05 find(Long id) { return new TaskDto05(id, "?", "?"); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {
    private final TaskService05 service;
    TaskController05(TaskService05 service) { this.service = service; }

    @GetMapping("/{id}")
    public TaskDto05 get(@PathVariable Long id) { return service.find(id); }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController05.class)
class TaskControllerTesterTest05 {

    // TODO: @Autowired MockMvcTester mvc;
    // TODO: @MockBean TaskService05 service;

    @Test
    void get_ok_fluent() {
        // TODO: when(service.find(1L)).thenReturn(new TaskDto05(1L, "Кофе", "NEW"));
        // TODO: assertThat(mvc.get().uri("/api/tasks/1"))
        //              .hasStatusOk()
        //              .bodyJson().extractingPath("$.title").isEqualTo("Кофе");
    }
}
