import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
class Runner01 implements CommandLineRunner {
    private final AccountRepository01 repo;
    private final AccountService01 service;
    Runner01(AccountRepository01 repo, AccountService01 service) { this.repo = repo; this.service = service; }

    @Override
    public void run(String... args) {
        Account01 a = repo.save(new Account01(1000));
        Account01 b = repo.save(new Account01(0));
        // TODO: service.transfer(a.getId(), b.getId(), 300); вывести балансы
        // TODO: try { service.transfer(a.getId(), b.getId(), 100000); } catch (Exception e) { ... }
        // TODO: вывести балансы снова — они не должны измениться после неуспешного перевода
    }
}
