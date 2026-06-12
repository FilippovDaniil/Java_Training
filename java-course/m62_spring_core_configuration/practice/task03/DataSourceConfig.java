package m62_spring_core_configuration.practice.task03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// ============================================================
// Конфигурация — объявляет бины с @Profile
// ============================================================

// TODO: @Configuration
class DataSourceConfig {

    // TODO: @Bean + @Profile("dev")
    public DataSource devDataSource() {
        // TODO: вернуть new DevDataSource()
        return null;
    }

    // TODO: @Bean + @Profile("prod")
    public DataSource prodDataSource() {
        // TODO: вернуть new ProdDataSource()
        return null;
    }
}
