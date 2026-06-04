/**
 * Задача 05 — Модуль 64: Конфигурация через application.properties и профили
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В application.properties задайте:
 *        shop.currency=RUB
 *        shop.tax-rate=20
 *   2) Внедрите эти значения в SettingsRunner05 через @Value.
 *      Для числа используйте int: @Value("${shop.tax-rate}") int taxRate.
 *   3) Реализуйте CommandLineRunner: выведите "Валюта: RUB, налог: 20%".
 *   4) ДОПОЛНИТЕЛЬНО (профили): создайте application-dev.properties со строкой
 *        shop.currency=USD
 *      и запустите с аргументом --spring.profiles.active=dev.
 *      Убедитесь, что значение переопределилось на USD.
 *
 * ПОДСКАЗКА:
 *   Профиль-специфичный файл application-<profile>.properties переопределяет
 *   значения из application.properties, когда профиль активен.
 *   См. также [модуль 62] про @Profile и Environment.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
