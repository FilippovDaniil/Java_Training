package m65_spring_boot_web_config.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @ConfigurationProperties(prefix = "shop")
class ShopProperties06 {
    private String currency;
    private int taxRate;
    private String supportEmail;

    // TODO: добавьте геттеры и сеттеры для всех трёх полей
}
