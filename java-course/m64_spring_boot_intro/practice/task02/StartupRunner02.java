package m64_spring_boot_intro.practice.task02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

// TODO: добавьте @Component
@Component
class StartupRunner02 implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // TODO: выведите "Приложение запущено!"
        System.out.println("Приложение запущено!");
        // TODO: выведите "Аргументов: " + args.length
        System.out.println("Аргументов: " + args.length);
    }
}
