package m62_spring_core_configuration.practice.task03;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

// ============================================================
// Конфигурация — объявляет бины с @Profile
// ============================================================

// TODO: @Configuration
@Configuration
class DataSourceConfig {

    private final Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    // TODO: @Bean + @Profile("dev")
    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        // TODO: вернуть new DevDataSource()
        return new DevDataSource(environment);
    }

    // TODO: @Bean + @Profile("prod")
    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        // TODO: вернуть new ProdDataSource()
        return new ProdDataSource(environment);
    }
}
