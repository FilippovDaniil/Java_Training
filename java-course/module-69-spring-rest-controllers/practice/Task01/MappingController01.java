import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/tasks")
class MappingController01 {

    // TODO: @GetMapping → "все задачи"
    public String all() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → "задача " + id
    public String one(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping("/{id}/comments/{cid}")
    public String comment(@PathVariable Long id, @PathVariable Long cid) {
        // TODO: верните "комментарий " + cid + " задачи " + id
        return null;
    }
}
