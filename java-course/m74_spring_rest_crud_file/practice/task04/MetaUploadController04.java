package m74_spring_rest_crud_file.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/files")
class MetaUploadController04 {

    private static final String UPLOAD_DIR = "uploads/";
    private final Map<String, FileMetaInfo> fileMetaStore = new ConcurrentHashMap<>();

    @PostMapping(value = "/with-meta", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadWithMeta(
            @RequestPart("file") MultipartFile file,
            @RequestPart("meta") AttachmentMeta04 meta) {

        try {
            // Проверка файла
            if (file.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Файл пуст");
            }

            // Проверка метаданных
            if (meta.title() == null || meta.title().isBlank()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Заголовок не может быть пустым");
            }

            // Сохраняем файл
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            // Сохраняем метаданные
            FileMetaInfo metaInfo = new FileMetaInfo(
                    meta.title(),
                    meta.author(),
                    file.getSize(),
                    file.getContentType(),
                    Instant.now()
            );
            fileMetaStore.put(fileName, metaInfo);

            // Успешный ответ
            String response = String.format(
                    "Файл %s '%s' от %s, размер %d байт, тип: %s",
                    fileName,
                    meta.title(),
                    meta.author(),
                    file.getSize(),
                    file.getContentType()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
