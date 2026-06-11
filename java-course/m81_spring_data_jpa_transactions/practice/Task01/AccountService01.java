package m81_spring_data_jpa_transactions.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class AccountService01 {
    private final AccountRepository01 repo;
    AccountService01(AccountRepository01 repo) { this.repo = repo; }

    // TODO: @Transactional
    public void transfer(Long fromId, Long toId, long amount) {
        // TODO: from = repo.findById(fromId).orElseThrow(); to = repo.findById(toId).orElseThrow();
        // TODO: if (from.getBalance() < amount) throw new IllegalStateException("Недостаточно средств");
        // TODO: from.setBalance(from.getBalance() - amount); to.setBalance(to.getBalance() + amount);
        // TODO: repo.save(from); repo.save(to);
    }
}
