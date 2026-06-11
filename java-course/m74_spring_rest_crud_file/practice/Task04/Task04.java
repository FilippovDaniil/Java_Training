package m74_spring_rest_crud_file.practice.task04;

/**
 * Задача 04 — Модуль 74: Файл + JSON-метаданные (@RequestPart)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   uploadWithMeta(): POST /api/files/with-meta (multipart/form-data, две части)
 *     - @RequestPart("file") MultipartFile file
 *     - @RequestPart("meta") AttachmentMeta04 meta   (JSON-часть: { "title":"...", "author":"..." })
 *     Верните "Файл <имя> '<meta.title>' от <meta.author>, размер <size>".
 *
 *   Проверьте через curl (две части — файл и json):
 *     curl -F "file=@a.txt" -F 'meta={"title":"Док","author":"ivan"};type=application/json' \
 *          http://localhost:8080/api/files/with-meta
 *
 * ЦЕЛЬ: принять одновременно бинарную и JSON-часть в одном multipart-запросе.
 *
 * ПОДСКАЗКА: @RequestPart умеет десериализовать JSON-часть в объект (через Jackson).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
