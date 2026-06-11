package m64_spring_boot_intro.practice.task05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

// TODO: добавьте @Component
class SettingsRunner05 implements CommandLineRunner {

    // TODO: @Value("${shop.currency}")
    private String currency;

    // TODO: @Value("${shop.tax-rate}")
    private int taxRate;

    @Override
    public void run(String... args) {
        // TODO: выведите "Валюта: " + currency + ", налог: " + taxRate + "%"
    }
}
