package m64_spring_boot_intro.practice.task06;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

// TODO: добавьте @Component
class DemoRunner06 implements CommandLineRunner {

    // TODO: объявите private final ClockService06 clock;
    // TODO: добавьте конструктор с инъекцией ClockService06

    @Override
    public void run(String... args) {
        // TODO: выведите "Старт в: " + clock.now()
    }
}
