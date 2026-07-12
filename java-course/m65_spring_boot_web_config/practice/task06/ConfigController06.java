package m65_spring_boot_web_config.practice.task06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ConfigController06 {

    // TODO: внедрите ShopProperties06 через конструктор
    private final ShopProperties06 props;

    @Autowired
    public ConfigController06(ShopProperties06 properties) {
        this.props = properties;
    }

    @GetMapping("/api/config")
    public String config() {
        // TODO: верните "Валюта=" + props.getCurrency() + ", налог=" + props.getTaxRate()
        //       + "%, поддержка=" + props.getSupportEmail()
        return "Валюта=" + props.getCurrency() + ", налог=" + props.getTaxRate()
                + "%, поддержка=" + props.getSupportEmail();
    }
}
