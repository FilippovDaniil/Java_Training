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

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

record AttachmentMeta04(String title, String author) {}

@RestController
@RequestMapping("/api/files")
class MetaUploadController04 {

    // TODO: @PostMapping(value = "/with-meta", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadWithMeta(
            /* @RequestPart("file") */ MultipartFile file,
            /* @RequestPart("meta") */ AttachmentMeta04 meta) {
        // TODO: верните "Файл " + file.getOriginalFilename() + " '" + meta.title()
        //       + "' от " + meta.author() + ", размер " + file.getSize()
        return null;
    }
}
