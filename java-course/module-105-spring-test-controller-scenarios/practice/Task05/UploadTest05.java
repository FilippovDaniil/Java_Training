/**
 * Задача 05 — Модуль 105: тест загрузки файла (multipart + MockMultipartFile)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер принимает файл-вложение (готов). Протестируйте загрузку:
 *     1) @WebMvcTest(AttachmentController05.class); @Autowired MockMvc; @MockBean AttachmentService05 service.
 *     2) uploads_file():
 *          MockMultipartFile file = new MockMultipartFile(
 *                  "file", "notes.txt", "text/plain", "привет".getBytes(UTF_8));
 *          when(service.store(any())).thenReturn("notes.txt");
 *          mockMvc.perform(multipart("/api/tasks/1/attachments").file(file))
 *                 .andExpect(status().isCreated())
 *                 .andExpect(jsonPath("$.filename").value("notes.txt"));
 *
 * ЦЕЛЬ: освоить multipart(...).file(...) и MockMultipartFile для тестов загрузки.
 *
 * ВАЖНО: первый аргумент MockMultipartFile ("file") = имя @RequestParam("file") в контроллере.
 *
 * ПОДСКАЗКА: multipart(uri) — это POST с multipart/form-data.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(AttachmentController05.class)
class UploadTest05 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean AttachmentService05 service;

    @Test
    void uploads_file() throws Exception {
        // TODO: MockMultipartFile file = new MockMultipartFile(
        // TODO:         "file", "notes.txt", "text/plain", "привет".getBytes(UTF_8));
        // TODO: when(service.store(any())).thenReturn("notes.txt");
        // TODO: mockMvc.perform(multipart("/api/tasks/1/attachments").file(file))
        //              .andExpect(status().isCreated())
        //              .andExpect(jsonPath("$.filename").value("notes.txt"));
    }
}
