import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class MappingController04 {

    private TaskEntity04 getStubEntity() {
        return new TaskEntity04();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable Long id) {
        // TODO: верните TaskMapper04.toResponse(getStubEntity())
        return null;
    }
}
