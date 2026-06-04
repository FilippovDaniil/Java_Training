import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

// TODO: добавьте @Component
class ConfigRunner03 implements ApplicationRunner {

    // TODO: @Value("${shop.welcome-message}")
    private String welcomeMessage;

    @Override
    public void run(ApplicationArguments args) {
        // TODO: выведите welcomeMessage
        // TODO: выведите "Опций передано: " + args.getOptionNames().size()
    }
}
