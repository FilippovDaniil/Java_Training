package m81_spring_data_jpa_transactions.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: @Service
class FixedService06 {
    // TODO: внедрите InnerService06
    public void outer(String name) {
        // TODO: inner.inner(name);   // вызов через внедрённый бин → прокси → транзакция работает
    }
}
