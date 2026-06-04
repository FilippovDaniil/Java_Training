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
