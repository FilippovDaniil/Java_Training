/**
 * Задача 04 — Модуль 64: Первый @RestController
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *   ВАЖНО: для эндпоинтов нужен именно starter-WEB (он тянет встроенный Tomcat).
 *
 * ЗАДАНИЕ:
 *   1) Пометьте GreetingController04 как @RestController.
 *   2) Метод hello() пометьте @GetMapping("/hello") — он должен возвращать строку
 *      "Привет от Spring Boot!".
 *   3) Метод greet(name) пометьте @GetMapping("/greet/{name}") и используйте
 *      @PathVariable, чтобы вернуть "Привет, <name>!".
 *   4) Запустите приложение и откройте в браузере:
 *        http://localhost:8080/hello
 *        http://localhost:8080/greet/Иван
 *
 * ПОДСКАЗКА:
 *   @GetMapping("/greet/{name}")
 *   public String greet(@PathVariable String name) { ... }
 *
 *   @RestController = @Controller + @ResponseBody (возврат пишется прямо в тело ответа).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
