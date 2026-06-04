/**
 * Задача 06 — Модуль 64: Автодетект @Service и инъекция в Runner
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Пометьте ClockService06 как @Service. Метод now() должен возвращать
 *      текущее время (java.time.LocalTime.now()) в виде строки.
 *   2) Пометьте DemoRunner06 как @Component (CommandLineRunner).
 *      Внедрите ClockService06 через конструктор (constructor injection).
 *   3) В run() выведите "Старт в: <время от сервиса>".
 *
 * ЦЕЛЬ:
 *   Показать, что @ComponentScan (внутри @SpringBootApplication) сам находит
 *   и @Service, и @Component, и связывает их через DI — без единой строки
 *   ручной конфигурации.
 *
 * ПОДСКАЗКА:
 *   Constructor injection — предпочтительный способ (см. модуль 60):
 *   private final ClockService06 clock;
 *   DemoRunner06(ClockService06 clock) { this.clock = clock; }
 */

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
