import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// ============================================================
// Сервисы (каркасы)
// ============================================================

// TODO: @Service
class AuditService07 {
    private final AuditRepository07 repo;
    AuditService07(AuditRepository07 repo) { this.repo = repo; }

    // TODO: @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String msg) {
        // TODO: repo.save(new Audit07(msg));
    }
}
