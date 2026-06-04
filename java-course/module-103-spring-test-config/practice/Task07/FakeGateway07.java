import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// Фейковая реализация шлюза для тестов (вместо реальной отправки).
class FakeGateway07 implements NotificationGateway07 {
    private final List<String> sent = new ArrayList<>();
    public void notify(String msg) { sent.add(msg); }
    List<String> sent() { return sent; }
}
