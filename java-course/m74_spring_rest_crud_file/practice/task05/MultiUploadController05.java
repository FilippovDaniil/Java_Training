package m74_spring_rest_crud_file.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
class MultiUploadController05 {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping(value = "/upload-many", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> uploadMany(@RequestParam("files") MultipartFile[] files) {

        if (files == null || files.length == 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Файлы не предоставлены"));
        }

        List<String> savedNames = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        long totalBytes = 0;

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    errors.add("Файл " + file.getOriginalFilename() + " пуст");
                    continue;
                }

                String fileName = file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                file.transferTo(filePath.toFile());

                savedNames.add(fileName);
                totalBytes += file.getSize();
            }

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("uploaded", savedNames.size());
            response.put("names", savedNames);
            response.put("totalBytes", totalBytes);

            if (!errors.isEmpty()) {
                response.put("errors", errors);
                return ResponseEntity
                        .status(HttpStatus.PARTIAL_CONTENT) // 206
                        .body(response);
            }

            return ResponseEntity
                    .status(HttpStatus.CREATED) // 201
                    .body(response);

        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Ошибка при сохранении: " + e.getMessage()
                    ));
        }
    }
}
