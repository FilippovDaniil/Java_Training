package m79_spring_data_jpa_repository.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

// TODO: @Component
class CatalogRunner07 implements CommandLineRunner {

    // TODO: внедрите ProductRepository07 (для сидинга) и CatalogService07

    @Override
    public void run(String... args) {
        // TODO: засейте ~8 товаров и вызовите методы сервиса, выводя результаты
    }
}
