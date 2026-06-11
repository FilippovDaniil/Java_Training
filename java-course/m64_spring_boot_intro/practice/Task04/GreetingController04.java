package m64_spring_boot_intro.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// TODO: добавьте @RestController
class GreetingController04 {

    // TODO: @GetMapping("/hello")
    public String hello() {
        // TODO: верните "Привет от Spring Boot!"
        return null;
    }

    // TODO: @GetMapping("/greet/{name}")
    public String greet(/* TODO: @PathVariable */ String name) {
        // TODO: верните "Привет, " + name + "!"
        return null;
    }
}
