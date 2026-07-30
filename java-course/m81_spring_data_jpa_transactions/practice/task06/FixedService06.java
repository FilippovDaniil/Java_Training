package m81_spring_data_jpa_transactions.practice.task06;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: @Service
@Service
class FixedService06 {
    // TODO: внедрите InnerService06
    private final InnerService06 service;

    @Autowired
    public FixedService06(InnerService06 service) {
        this.service = service;
    }

    public void outer(String name) {
        // TODO: inner.inner(name);   // вызов через внедрённый бин → прокси → транзакция работает
        service.inner(name);
    }
}
