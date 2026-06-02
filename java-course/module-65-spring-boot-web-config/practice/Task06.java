/**
 * Задача 06 — Модуль 65: @ConfigurationProperties — типизированная конфигурация
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В application.properties задайте блок:
 *        shop.currency=RUB
 *        shop.tax-rate=20
 *        shop.support-email=help@shop.ru
 *   2) Класс ShopProperties06 пометьте @ConfigurationProperties(prefix = "shop").
 *      Поля: currency (String), taxRate (int), supportEmail (String) + геттеры/сеттеры.
 *      ВНИМАНИЕ: shop.tax-rate ↔ поле taxRate (relaxed binding — дефис → camelCase).
 *   3) Активируйте свойства: добавьте @EnableConfigurationProperties(ShopProperties06.class)
 *      на класс Task06.
 *   4) В ConfigController06 внедрите ShopProperties06 и в @GetMapping("/api/config")
 *      верните строку "Валюта=RUB, налог=20%, поддержка=help@shop.ru".
 *
 * ЦЕЛЬ: показать преимущество @ConfigurationProperties над россыпью @Value.
 *
 * ПОДСКАЗКА:
 *   relaxed binding: shop.tax-rate, shop.taxRate, SHOP_TAX_RATE — всё свяжется с taxRate.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// TODO: @EnableConfigurationProperties(ShopProperties06.class)
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

// TODO: @ConfigurationProperties(prefix = "shop")
class ShopProperties06 {
    private String currency;
    private int taxRate;
    private String supportEmail;

    // TODO: добавьте геттеры и сеттеры для всех трёх полей
}

@RestController
class ConfigController06 {

    // TODO: внедрите ShopProperties06 через конструктор

    @GetMapping("/api/config")
    public String config() {
        // TODO: верните "Валюта=" + props.getCurrency() + ", налог=" + props.getTaxRate()
        //       + "%, поддержка=" + props.getSupportEmail()
        return null;
    }
}
