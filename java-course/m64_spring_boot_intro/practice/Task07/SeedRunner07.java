package m64_spring_boot_intro.practice.task07;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// Сидинг при старте (каркас)
// ============================================================

// TODO: добавьте @Component
class SeedRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductService07 через конструктор

    @Override
    public void run(String... args) {
        // TODO: добавьте "Ноутбук", "Мышь", "Клавиатура"
        // TODO: выведите "Засеяно товаров: " + service.count()
    }
}
