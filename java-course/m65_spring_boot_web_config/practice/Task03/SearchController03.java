package m65_spring_boot_web_config.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/products")
class SearchController03 {

    // TODO: @GetMapping("/search")
    public String search(
            /* TODO: @RequestParam */ String category,
            /* TODO: @RequestParam(defaultValue = "0") */ int page,
            /* TODO: @RequestParam(defaultValue = "20") */ int size,
            /* TODO: @RequestParam(required = false) */ String name) {
        // TODO: верните строку-описание (если name == null — напишите "нет")
        return null;
    }

    // TODO: @GetMapping("/{category}/{id}")
    public String byCategoryAndId(/* TODO: @PathVariable */ String category,
                                  /* TODO: @PathVariable */ Long id) {
        // TODO: верните "Товар " + id + " в категории " + category
        return null;
    }
}
