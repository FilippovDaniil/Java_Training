package m74_spring_rest_crud_file.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
