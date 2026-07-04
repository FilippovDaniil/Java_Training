package m62_spring_core_configuration.practice.task03;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

// TODO: @Profile("prod")
class ProdDataSource implements DataSource {

    private final Environment env;

    public ProdDataSource(Environment env) {
        this.env = env;
    }

    @Override
    public String getUrl() {
        // TODO: вернуть строку подключения PostgreSQL
        return env.getProperty("db.url");
    }
}
