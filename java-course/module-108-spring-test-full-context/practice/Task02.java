/**
 * Задача 02 — Модуль 108: RANDOM_PORT + TestRestTemplate (реальный HTTP) — GET
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Поднимите реальный сервер и обратитесь по настоящему HTTP:
 *     1) @SpringBootTest(classes = Task02.class, webEnvironment = RANDOM_PORT);
 *        @Autowired TestRestTemplate restTemplate.
 *     2) get_over_http():
 *          ResponseEntity<String> resp = restTemplate.getForEntity("/api/ping", String.class);
 *          assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
 *          assertThat(resp.getBody()).isEqualTo("pong");
 *
 * ЦЕЛЬ: освоить RANDOM_PORT + TestRestTemplate — тест через настоящий сетевой слой.
 *
 * ВАЖНО: путь относительный ("/api/ping") — базовый URL с портом TestRestTemplate подставит сам.
 *
 * ПОДСКАЗКА: RANDOM_PORT поднимает встроенный сервер на случайном свободном порту.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) { SpringApplication.run(Task02.class, args); }
}

@RestController
@RequestMapping("/api/ping")
class PingController02 {
    @GetMapping
    String ping() { return "pong"; }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(
// TODO:     classes = Task02.class,
// TODO:     webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpGetTest02 {

    // TODO: @Autowired TestRestTemplate restTemplate;

    @Test
    void get_over_http() {
        // TODO: ResponseEntity<String> resp = restTemplate.getForEntity("/api/ping", String.class);
        // TODO: assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO: assertThat(resp.getBody()).isEqualTo("pong");
    }
}
