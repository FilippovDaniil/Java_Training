package m67_spring_rest_http_backend.practice.task02;

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
        URI location = URI.create("/api/tasks/42");
        // TODO: return ResponseEntity.created(location).body("Задача создана");
        return ResponseEntity.created(location).body("Задача создана");
    }

    @GetMapping("/api/whoami")
    public String whoAmI(/* TODO: @RequestHeader(value="Authorization", required=false) */
            @RequestHeader(value="Authorization", required=false) String auth) {
        // TODO: если auth == null — верните "Токен не передан"
        if (auth == null){
            return "Токен не передан";
        }
        // TODO: иначе — верните "Токен: " + auth
        else {
            return "Токен: " + auth;
        }
    }
}
