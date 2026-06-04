import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// «Внешний ресурс»: адрес определяется в рантайме (имитация брокера/контейнера).
class FakeBroker06 {
    static String url() { return "tcp://localhost:61616"; }
}
