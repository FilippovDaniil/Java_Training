import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task04.class)
class AsyncServiceTest04 {

    // TODO: @Autowired ReportService04 service;

    @Test
    void async_result() throws Exception {
        // TODO: CompletableFuture<String> future = service.generateReport();
        // TODO: String result = future.get(2, TimeUnit.SECONDS);
        // TODO: assertThat(result).isEqualTo("READY");
    }
}
