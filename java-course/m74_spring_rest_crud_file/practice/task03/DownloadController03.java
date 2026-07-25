package m74_spring_rest_crud_file.practice.task03;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/files")
class DownloadController03 {

    @GetMapping("/download/{name}")
    public ResponseEntity<byte[]> download(@PathVariable("name") String name) {
        // Создаем содержимое файла
        byte[] data = ("Содержимое файла " + name).getBytes(StandardCharsets.UTF_8);

        // Формируем заголовок Content-Disposition
        String contentDisposition = "attachment; filename=\"" + name + "\"";

        // Возвращаем ResponseEntity
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(data);
    }
}