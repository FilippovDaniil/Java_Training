package m75_spring_rest_config_openapi.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class HeaderVersionController02 {

    // TODO: @GetMapping(value = "/{id}", headers = "X-API-Version=1")
    public String getV1(@PathVariable Long id) {
        // TODO: верните "v1: задача " + id
        return null;
    }

    // TODO: @GetMapping(value = "/{id}", headers = "X-API-Version=2")
    public String getV2(@PathVariable Long id) {
        // TODO: верните "v2: задача " + id + " [NEW]"
        return null;
    }
}
