import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
class HeaderController02 {

    @PostMapping("/api/tasks")
    public ResponseEntity<String> create() {
        // TODO: URI location = URI.create("/api/tasks/42");
        // TODO: return ResponseEntity.created(location).body("Задача создана");
        return null;
    }

    @GetMapping("/api/whoami")
    public String whoAmI(/* TODO: @RequestHeader(value="Authorization", required=false) */ String auth) {
        // TODO: если auth == null — верните "Токен не передан"
        // TODO: иначе — верните "Токен: " + auth
        return null;
    }
}
