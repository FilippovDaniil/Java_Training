package m74_spring_rest_crud_file.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/tasks")
class AttachmentController07 {

    private final Map<Long, List<Attachment07>> store = new ConcurrentHashMap<>();

    // ========== UPLOAD ==========
    @PostMapping(value = "/{taskId}/attachments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(
            @PathVariable Long taskId,
            @RequestParam("file") MultipartFile file) {

        try {
            // Создаем вложение
            Attachment07 attachment = new Attachment07(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );

            // Сохраняем в хранилище
            store.computeIfAbsent(taskId, k -> new ArrayList<>()).add(attachment);

            // Возвращаем ответ
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Загружено: " + file.getOriginalFilename() + " к задаче " + taskId);

        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при загрузке файла: " + e.getMessage());
        }
    }

    // ========== LIST ==========
    @GetMapping("/{taskId}/attachments")
    public List<String> list(@PathVariable Long taskId) {
        List<Attachment07> attachments = store.get(taskId);
        if (attachments == null || attachments.isEmpty()) {
            return List.of();
        }
        return attachments.stream()
                .map(Attachment07::name)
                .toList();
    }

    // ========== DOWNLOAD ==========
    @GetMapping("/{taskId}/attachments/{index}")
    public ResponseEntity<byte[]> download(
            @PathVariable Long taskId,
            @PathVariable int index) {

        // Проверяем существование задачи
        List<Attachment07> attachments = store.get(taskId);
        if (attachments == null) {
            return ResponseEntity.notFound().build();
        }

        // Проверяем границы индекса
        if (index < 0 || index >= attachments.size()) {
            return ResponseEntity.notFound().build();
        }

        // Получаем вложение
        Attachment07 attachment = attachments.get(index);

        // Формируем заголовок для скачивания
        String contentDisposition = "attachment; filename=\"" + attachment.name() + "\"";

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(attachment.contentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(attachment.content());
    }

    // ========== EXPORT CSV ==========
    @GetMapping(value = "/export", produces = "text/csv")
    public ResponseEntity<String> exportTasksCsv() {
        StringBuilder csv = new StringBuilder();

        // Заголовок
        csv.append("taskId,attachmentsCount\n");

        // Данные
        for (Map.Entry<Long, List<Attachment07>> entry : store.entrySet()) {
            Long taskId = entry.getKey();
            int count = entry.getValue().size();
            csv.append(taskId).append(",").append(count).append("\n");
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"tasks.csv\"")
                .body(csv.toString());
    }
}
