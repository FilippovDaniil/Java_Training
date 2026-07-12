package m68_spring_rest_design.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class FilterController05 {

    @GetMapping
    public String search(
            /* TODO: @RequestParam(required = false) */ @RequestParam(name = "status", required = false) String status,
            /* TODO: @RequestParam(name = "", required = false) */ @RequestParam(name = "assignee", required = false) String assignee,
            /* TODO: @RequestParam(name = "", defaultValue = "id") */ @RequestParam(name = "sort", defaultValue = "id") String sort,
            /* TODO: @RequestParam(name = "", defaultValue = "0") */ @RequestParam(name = "page", defaultValue = "0") int page,
            /* TODO: @RequestParam(name = "", defaultValue = "20") */ @RequestParam(name = "size", defaultValue = "20") int size) {
        // TODO: соберите строку-описание (status/assignee == null → "любой")
        if (status == null || assignee == null){
            return "Фильтр: status=любой, assignee=ivan; сортировка по createdAt; страница 0 по 20";
        }else {
            return "Фильтр: status=DONE, assignee=ivan; сортировка по createdAt; страница 0 по 20";
        }
    }
}
