package m65_spring_boot_web_config.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/products")
@RestController
@RequestMapping("/api/products")
class SearchController03 {

    // TODO: @GetMapping("/search")
    @GetMapping("/search")
    public String search(
            /* TODO: @RequestParam */ @RequestParam(name = "category") String category,
            /* TODO: @RequestParam(defaultValue = "0") */ @RequestParam(name = "page", defaultValue = "0") int page,
            /* TODO: @RequestParam(defaultValue = "20") */ @RequestParam(name = "size", defaultValue = "20") int size,
            /* TODO: @RequestParam(required = false) */ @RequestParam(name = "name", required = false) String name) {
        // TODO: верните строку-описание (если name == null — напишите "нет")
        return name != null ? name : null;
    }

    // TODO: @GetMapping("/{category}/{id}")
    @GetMapping("/{category}/{id}")
    public String byCategoryAndId(/* TODO: @PathVariable */ @PathVariable("category") String category,
                                  /* TODO: @PathVariable */ @PathVariable("id") Long id) {
        // TODO: верните "Товар " + id + " в категории " + category
        return "Товар: " + id + " в категории: " + category;
    }
}
