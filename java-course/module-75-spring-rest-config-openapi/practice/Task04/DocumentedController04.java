import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tasks")
class DocumentedController04 {

    // TODO: @Operation(summary = "...", description = "...")
    // TODO: @ApiResponse(responseCode = "200", description = "...")
    // TODO: @ApiResponse(responseCode = "404", description = "...")
    @GetMapping("/{id}")
    public TaskDto04 get(@PathVariable Long id) {
        if (id <= 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не найдено");
        return new TaskDto04(id, "Купить кофе");
    }
}
