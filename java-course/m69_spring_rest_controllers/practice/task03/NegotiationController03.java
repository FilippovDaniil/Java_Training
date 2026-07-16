package m69_spring_rest_controllers.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class NegotiationController03 {

    // TODO: @PostMapping(consumes = "application/json", produces = "application/json")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public CreateTaskDto createJson(/* @RequestBody */ @RequestBody CreateTaskDto dto) {
        // TODO: верните dto
        return dto;
    }

    // TODO: @GetMapping → "все задачи"
    @GetMapping
    public String listAll() {
        return "все задачи";
    }

    // TODO: @GetMapping(params = "status") → "задачи со статусом " + status
    @GetMapping(params = "status")
    public String listByStatus(/* @RequestParam */ @RequestParam("status") String status) {
        return "задачи со статусом " + status;
    }
}
