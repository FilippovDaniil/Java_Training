package m108_spring_test_full_context.practice.task04;

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
