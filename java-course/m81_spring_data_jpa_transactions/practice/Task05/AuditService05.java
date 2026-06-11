package m81_spring_data_jpa_transactions.practice.task05;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
class AuditService05 {
    private final AuditRepository05 repo;
    AuditService05(AuditRepository05 repo) { this.repo = repo; }

    // TODO: @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String msg) {
        repo.save(new Audit05(msg));
    }
}
