package m74_spring_rest_crud_file.practice.task03;

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
