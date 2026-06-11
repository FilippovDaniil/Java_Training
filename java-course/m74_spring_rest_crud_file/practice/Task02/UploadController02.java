package m74_spring_rest_crud_file.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
class UploadController02 {

    // TODO: @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(/* @RequestParam("file") */ MultipartFile file) {
        // TODO: if (file.isEmpty()) → 400 "Файл пуст"
        // TODO: иначе → 200 "Загружен: " + имя + " (" + размер + " байт), тип: " + contentType
        return null;
    }
}
