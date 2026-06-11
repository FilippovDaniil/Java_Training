package m103_spring_test_config.practice.task07;

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

// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task07.class)
// TODO: @org.springframework.test.context.ActiveProfiles("test")
// TODO: @org.springframework.test.context.TestPropertySource(properties = "app.max-tasks=2")
// TODO: @org.springframework.context.annotation.Import(TestBeans07.class)
class LayeredConfigTest07 {

    // TODO: @org.springframework.beans.factory.annotation.Autowired QuotaService07 quota;
    // TODO: @org.springframework.beans.factory.annotation.Autowired NotificationGateway07 gateway;
    // TODO: @Value("${app.environment}") String env;

    @Test
    void env_is_test() {
        // TODO: assertThat(env).isEqualTo("test");
    }

    @Test
    void quota_respects_limit() {
        // TODO: assertThat(quota.canAdd(1)).isTrue();
        // TODO: assertThat(quota.canAdd(2)).isFalse();   // лимит app.max-tasks=2
    }

    @Test
    void fake_gateway_used() {
        // TODO: gateway.notify("x");
        // TODO: assertThat(((FakeGateway07) gateway).sent()).contains("x");
    }
}
