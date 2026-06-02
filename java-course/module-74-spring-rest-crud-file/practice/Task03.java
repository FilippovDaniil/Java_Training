/**
 * Задача 03 — Модуль 74: Выгрузка файла (download) с Content-Disposition
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   download(): GET /api/files/download/{name}
 *     Сформируйте «файл» из текста и отдайте его на скачивание:
 *       - byte[] data = ("Содержимое файла " + name).getBytes(StandardCharsets.UTF_8);
 *       - вернуть ResponseEntity.ok()
 *             .contentType(MediaType.APPLICATION_OCTET_STREAM)
 *             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + ".txt\"")
 *             .body(data);
 *
 *   Проверьте в браузере: GET /api/files/download/report → скачается report.txt.
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем "attachment" отличается от "inline" в заголовке Content-Disposition?
 *
 * ПОДСКАЗКА: именно Content-Disposition: attachment заставляет браузер СКАЧАТЬ файл.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

@RestController
@RequestMapping("/api/files")
class DownloadController03 {

    // TODO: @GetMapping("/download/{name}")
    public ResponseEntity<byte[]> download(@PathVariable String name) {
        // TODO: byte[] data = ("Содержимое файла " + name).getBytes(StandardCharsets.UTF_8);
        // TODO: верните ResponseEntity с APPLICATION_OCTET_STREAM и Content-Disposition: attachment
        return null;
    }
}
