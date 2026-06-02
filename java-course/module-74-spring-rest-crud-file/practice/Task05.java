/**
 * Задача 05 — Модуль 74: Загрузка нескольких файлов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   uploadMany(): POST /api/files/upload-many (multipart/form-data, поле "files" повторяется)
 *     - @RequestParam("files") MultipartFile[] files
 *     - верните Map<String, Object>:
 *         "count" → files.length
 *         "names" → список оригинальных имён
 *         "totalBytes" → сумма размеров всех файлов
 *
 *   Проверьте через curl (несколько -F с одинаковым именем "files"):
 *     curl -F "files=@a.txt" -F "files=@b.txt" http://localhost:8080/api/files/upload-many
 *
 * ЦЕЛЬ: принять массив файлов одним запросом.
 *
 * ПОДСКАЗКА:
 *   Arrays.stream(files).map(MultipartFile::getOriginalFilename).toList()
 *   Arrays.stream(files).mapToLong(MultipartFile::getSize).sum()
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@RestController
@RequestMapping("/api/files")
class MultiUploadController05 {

    // TODO: @PostMapping(value = "/upload-many", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadMany(/* @RequestParam("files") */ MultipartFile[] files) {
        // TODO: соберите Map с count, names, totalBytes
        return null;
    }
}
