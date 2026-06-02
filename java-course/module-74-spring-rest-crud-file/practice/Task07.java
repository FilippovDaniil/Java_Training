/**
 * Задача 07 — Модуль 74: МИНИ-ПРОЕКТ «Вложения задач Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: реализовать работу с вложениями задачи — загрузка, список, скачивание,
 *       плюс CSV-экспорт всех задач. Хранилище — в памяти.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Хранилище вложений: Map<Long, List<Attachment07>>, где ключ — taskId.
 *   Attachment07 — record (String name, String contentType, byte[] content).
 *
 *   AttachmentController07 (@RequestMapping("/api/tasks")):
 *     1) upload(taskId, file):
 *          POST /api/tasks/{taskId}/attachments (multipart, поле "file")
 *          - сохранить Attachment07 в список задачи
 *          - 201 + "Загружено: <name> к задаче <taskId>"
 *     2) list(taskId):
 *          GET /api/tasks/{taskId}/attachments
 *          - вернуть список ИМЁН вложений (List<String>), пустой → []
 *     3) download(taskId, index):
 *          GET /api/tasks/{taskId}/attachments/{index}
 *          - вернуть содержимое вложения по индексу:
 *            ResponseEntity с правильным contentType и Content-Disposition: attachment
 *          - если задачи/индекса нет → 404
 *     4) exportTasksCsv():
 *          GET /api/tasks/export (produces "text/csv")
 *          - вернуть CSV "taskId,attachmentsCount" по всем задачам, как файл tasks.csv
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP multipart] ──► upload ──► Map<taskId, List<Attachment07>>
 *   [HTTP GET]       ──► list / download / export ◄── (чтение из той же Map)
 *
 * ПОДСКАЗКА:
 *   store.computeIfAbsent(taskId, k -> new ArrayList<>()).add(att);
 *   download: проверьте границы списка перед get(index) → иначе 404.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

record Attachment07(String name, String contentType, byte[] content) {}

@RestController
@RequestMapping("/api/tasks")
class AttachmentController07 {

    private final Map<Long, List<Attachment07>> store = new ConcurrentHashMap<>();

    // TODO: @PostMapping(value = "/{taskId}/attachments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@PathVariable Long taskId,
                                         /* @RequestParam("file") */ MultipartFile file) {
        // TODO: создайте Attachment07 из file (name, contentType, getBytes())
        // TODO: store.computeIfAbsent(taskId, k -> new ArrayList<>()).add(att)
        // TODO: 201 + "Загружено: <name> к задаче <taskId>"
        return null;
    }

    // TODO: @GetMapping("/{taskId}/attachments")
    public List<String> list(@PathVariable Long taskId) {
        // TODO: вернуть имена вложений задачи (или List.of(), если их нет)
        return null;
    }

    // TODO: @GetMapping("/{taskId}/attachments/{index}")
    public ResponseEntity<byte[]> download(@PathVariable Long taskId, @PathVariable int index) {
        // TODO: достать список; если нет / индекс вне границ → 404
        // TODO: иначе вернуть содержимое с contentType и Content-Disposition: attachment
        return null;
    }

    // TODO: @GetMapping(value = "/export", produces = "text/csv")
    public ResponseEntity<String> exportTasksCsv() {
        // TODO: CSV "taskId,attachmentsCount" по всем ключам store, как файл tasks.csv
        return null;
    }
}
