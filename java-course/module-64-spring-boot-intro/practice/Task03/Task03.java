/**
 * Задача 03 — Модуль 64: ApplicationRunner + чтение свойства через @Value
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Создайте файл src/main/resources/application.properties и добавьте строку:
 *        shop.welcome-message=Добро пожаловать в магазин!
 *   2) В ConfigRunner03 внедрите это свойство через @Value("${shop.welcome-message}").
 *   3) Реализуйте интерфейс ApplicationRunner: метод run(ApplicationArguments args).
 *      Выведите приветствие, а также количество "опциональных" аргументов
 *      args.getOptionNames().size() (опции вида --key=value).
 *   4) Пометьте класс как @Component.
 *
 * ОТЛИЧИЕ ОТ Task02:
 *   CommandLineRunner   → run(String... args)            — сырые аргументы
 *   ApplicationRunner   → run(ApplicationArguments args) — разобранные опции (--key=value)
 *
 * ПОДСКАЗКА:
 *   args.getOptionNames() возвращает Set<String> имён опций (для --name=Иван это "name").
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
