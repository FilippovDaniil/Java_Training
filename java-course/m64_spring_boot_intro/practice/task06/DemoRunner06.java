package m64_spring_boot_intro.practice.task06;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

// TODO: добавьте @Component
@Component
class DemoRunner06 implements CommandLineRunner {

    // TODO: объявите private final ClockService06 clock;
    private final ClockService06 service;
    // TODO: добавьте конструктор с инъекцией ClockService06
    public DemoRunner06(ClockService06 service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        // TODO: выведите "Старт в: " + clock.now()
        System.out.println("Старт в: " + service.now());
    }
}
