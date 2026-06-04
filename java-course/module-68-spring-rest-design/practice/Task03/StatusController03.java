import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class StatusController03 {

    // TODO: @PatchMapping("/{id}")
    public String changeStatus(@PathVariable Long id, /* @RequestBody */ StatusPatchDto dto) {
        // TODO: верните "Статус задачи " + id + " → " + dto.status()
        return null;
    }
}
