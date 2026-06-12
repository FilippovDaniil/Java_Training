package m70_spring_rest_dto_json.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// TODO: record AssigneeDto06(Long id, String name)
// TODO: record TaskDetailDto06(Long id, String title, AssigneeDto06 assignee, List<String> tags)

@RestController
@RequestMapping("/api/tasks")
class NestedController06 {

    @GetMapping("/{id}")
    public Object getOne(@PathVariable Long id) {
        // TODO: соберите TaskDetailDto06 с вложенным AssigneeDto06(7, "Иван")
        //       и tags = List.of("покупки", "срочно"); верните его
        return null;
    }
}
