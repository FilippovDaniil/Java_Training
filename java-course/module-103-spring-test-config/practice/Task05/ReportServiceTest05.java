import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import static org.assertj.core.api.Assertions.assertThat;

// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task05.class)
// TODO: @org.springframework.context.annotation.Import(TestClockConfig05.class)
class ReportServiceTest05 {

    // TODO: @org.springframework.beans.factory.annotation.Autowired ReportService05 service;

    @Test
    void uses_fixed_clock() {
        // TODO: assertThat(service.today()).isEqualTo("2026-01-01");
    }
}
