/**
 * Задача 06 — Модуль 105: тест выгрузки файла (байты + Content-Disposition)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер отдаёт файл на скачивание (готов). Проверьте заголовок и содержимое:
 *     1) @WebMvcTest(DownloadController06.class); @Autowired MockMvc; @MockBean AttachmentService06 service.
 *     2) downloads_file():
 *          when(service.load("notes.txt")).thenReturn("привет".getBytes(UTF_8));
 *          mockMvc.perform(get("/api/tasks/1/attachments/notes.txt"))
 *                 .andExpect(status().isOk())
 *                 .andExpect(header().string("Content-Disposition", containsString("attachment")))
 *                 .andExpect(content().bytes("привет".getBytes(UTF_8)));
 *
 * ЦЕЛЬ: тестировать выгрузку — проверка Content-Disposition: attachment и точных байтов тела.
 *
 * ВАЖНО: content().bytes(...) сверяет «сырое» тело; кодировку байтов держите единой (UTF_8).
 *
 * ПОДСКАЗКА: containsString("attachment") — заголовок обычно вида attachment; filename="...".
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(DownloadController06.class)
class DownloadTest06 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean AttachmentService06 service;

    @Test
    void downloads_file() throws Exception {
        // TODO: when(service.load("notes.txt")).thenReturn("привет".getBytes(UTF_8));
        // TODO: mockMvc.perform(get("/api/tasks/1/attachments/notes.txt"))
        //              .andExpect(status().isOk())
        //              .andExpect(header().string("Content-Disposition", containsString("attachment")))
        //              .andExpect(content().bytes("привет".getBytes(UTF_8)));
    }
}
