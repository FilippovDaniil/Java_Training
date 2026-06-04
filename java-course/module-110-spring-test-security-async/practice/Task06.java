/**
 * Задача 06 — Модуль 110: документирование API через Spring REST Docs
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-test, org.springframework.restdocs:spring-restdocs-mockmvc.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Сгенерируйте документацию ИЗ ТЕСТА — проходящий тест создаёт сниппеты:
 *     1) @WebMvcTest(TaskController06.class) + @AutoConfigureRestDocs; @Autowired MockMvc; @MockBean TaskService06 service.
 *     2) documents_get_task():
 *          when(service.find(1L)).thenReturn(new TaskDto06(1L, "Кофе", "NEW"));
 *          mockMvc.perform(get("/api/tasks/1"))
 *                 .andExpect(status().isOk())
 *                 .andDo(document("get-task",
 *                         responseFields(
 *                             fieldWithPath("id").description("Идентификатор"),
 *                             fieldWithPath("title").description("Заголовок"),
 *                             fieldWithPath("status").description("Статус"))));
 *
 * ЦЕЛЬ: освоить REST Docs — документация генерируется из теста и не «протухает» (рассинхрон ломает тест).
 *
 * ВАЖНО: статические импорты document/responseFields/fieldWithPath — из org.springframework.restdocs.*.
 *        Сниппеты появятся в build/generated-snippets/get-task/.
 *
 * ПОДСКАЗКА: отличие от springdoc (модуль 75) — там спека из аннотаций, здесь из реальных тестов.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- DTO, сервис, контроллер (готовы) ---
record TaskDto06(Long id, String title, String status) {}

@Service
class TaskService06 {
    public TaskDto06 find(Long id) { return new TaskDto06(id, "?", "?"); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController06 {
    private final TaskService06 service;
    TaskController06(TaskService06 service) { this.service = service; }

    @GetMapping("/{id}")
    public TaskDto06 get(@PathVariable Long id) { return service.find(id); }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController06.class)
// TODO: @AutoConfigureRestDocs
class TaskDocsTest06 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService06 service;

    @Test
    void documents_get_task() throws Exception {
        // TODO: when(service.find(1L)).thenReturn(new TaskDto06(1L, "Кофе", "NEW"));
        // TODO: mockMvc.perform(get("/api/tasks/1"))
        //              .andExpect(status().isOk())
        //              .andDo(document("get-task",
        //                      responseFields(
        //                          fieldWithPath("id").description("Идентификатор"),
        //                          fieldWithPath("title").description("Заголовок"),
        //                          fieldWithPath("status").description("Статус"))));
    }
}
