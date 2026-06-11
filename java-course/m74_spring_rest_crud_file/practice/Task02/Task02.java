package m74_spring_rest_crud_file.practice.task02;

/**
 * Задача 02 — Модуль 74: Загрузка файла (MultipartFile)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   upload(): POST /api/files/upload  (Content-Type: multipart/form-data, поле "file")
 *     - @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
 *     - @RequestParam("file") MultipartFile file
 *     - если file.isEmpty() → 400 "Файл пуст"
 *     - иначе → 200 "Загружен: <имя> (<размер> байт), тип: <contentType>"
 *
 *   Проверьте через curl:
 *     curl -F "file=@/path/to/pic.png" http://localhost:8080/api/files/upload
 *
 * ЦЕЛЬ: принять бинарный файл и прочитать его метаданные.
 *
 * ПОДСКАЗКА:
 *   file.getOriginalFilename(), file.getSize(), file.getContentType(), file.isEmpty().
 *   Лимиты размера: spring.servlet.multipart.max-file-size в application.properties.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
