import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(
// TODO:     classes = Task03.class, properties = "app.name=TaskTracker-Test")
class InlinePropsTest03 {

    // TODO: @org.springframework.beans.factory.annotation.Autowired AppInfo03 info;

    @Test
    void inline_property() {
        // TODO: assertThat(info.name()).isEqualTo("TaskTracker-Test");
    }
}
