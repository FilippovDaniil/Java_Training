package m70_spring_rest_dto_json.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
class EnvelopeController05 {

    // TODO: @GetMapping("/{id}") → ApiResponse<TaskDto05>
    public ApiResponse<TaskDto05> getTask(@PathVariable Long id) {
        // TODO: return ApiResponse.ok(new TaskDto05(id, "Купить кофе"));
        return null;
    }

    // TODO: @GetMapping → ApiResponse<List<TaskDto05>>
    public ApiResponse<List<TaskDto05>> getList() {
        // TODO: return ApiResponse.ok(List.of(new TaskDto05(1L, "A"), new TaskDto05(2L, "B")));
        return null;
    }
}
