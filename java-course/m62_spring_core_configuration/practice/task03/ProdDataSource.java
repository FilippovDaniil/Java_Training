package m62_spring_core_configuration.practice.task03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// TODO: @Profile("prod")
class ProdDataSource implements DataSource {
    @Override
    public String getUrl() {
        // TODO: вернуть строку подключения PostgreSQL
        return null;
    }
}
