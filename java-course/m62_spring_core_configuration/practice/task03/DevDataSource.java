package m62_spring_core_configuration.practice.task03;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

// ============================================================
// Реализации — TODO: добавьте @Profile к каждой
// ============================================================

// TODO: @Profile("dev")
class DevDataSource implements DataSource {

    private final Environment env;

    public DevDataSource(Environment env) {
        this.env = env;
    }

    @Override
    public String getUrl() {
        // TODO: вернуть строку подключения H2 in-memory
        return env.getProperty("db.url");
    }
}
