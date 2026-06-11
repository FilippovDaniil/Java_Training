package m105_spring_test_controller_scenarios.practice.task05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestController
@RequestMapping("/api/tasks")
class AttachmentController05 {
    private final AttachmentService05 service;
    AttachmentController05(AttachmentService05 service) { this.service = service; }

    @PostMapping("/{id}/attachments")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return Map.of("filename", service.store(file));
    }
}
