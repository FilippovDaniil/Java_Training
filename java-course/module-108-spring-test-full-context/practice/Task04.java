/**
 * Задача 04 — Модуль 108: @LocalServerPort — явный доступ к порту
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Иногда нужен сам номер порта (для своего клиента/логирования). Получите его через @LocalServerPort:
 *     1) @SpringBootTest(classes = Task04.class, webEnvironment = RANDOM_PORT);
 *        @LocalServerPort int port; @Autowired TestRestTemplate restTemplate.
 *     2) port_is_assigned(): assertThat(port).isGreaterThan(0).
 *     3) absolute_url_works():
 *          String url = "http://localhost:" + port + "/api/ping";
 *          assertThat(restTemplate.getForObject(url, String.class)).isEqualTo("pong");
 *
 * ЦЕЛЬ: узнать назначенный случайный порт через @LocalServerPort и собрать абсолютный URL.
 *
 * ВАЖНО: @LocalServerPort заполняется реальным портом ТОЛЬКО при RANDOM_PORT/DEFINED_PORT.
 *
 * ПОДСКАЗКА: getForObject возвращает сразу тело (без ResponseEntity).
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) { SpringApplication.run(Task04.class, args); }
}

@RestController
@RequestMapping("/api/ping")
class PingController04 {
    @GetMapping
    String ping() { return "pong"; }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(
// TODO:     classes = Task04.class,
// TODO:     webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocalPortTest04 {

    // TODO: @LocalServerPort int port;
    // TODO: @Autowired TestRestTemplate restTemplate;

    @Test
    void port_is_assigned() {
        // TODO: assertThat(port).isGreaterThan(0);
    }

    @Test
    void absolute_url_works() {
        // TODO: String url = "http://localhost:" + port + "/api/ping";
        // TODO: assertThat(restTemplate.getForObject(url, String.class)).isEqualTo("pong");
    }
}
