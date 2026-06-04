import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: record CreateTaskRequest(String title, String assignee)
// TODO: record TaskResponse(Long id, String title, String assignee, String status)

@RestController
@RequestMapping("/api/tasks")
class DtoController01 {

    // TODO: @PostMapping
    public Object create(/* @RequestBody CreateTaskRequest req */ Object req) {
        // TODO: верните new TaskResponse(1L, req.title(), req.assignee(), "NEW")
        return null;
    }
}
