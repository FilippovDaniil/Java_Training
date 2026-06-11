package m81_spring_data_jpa_transactions.practice.task01;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Entity @Table(name = "accounts")
class Account01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long balance;
    protected Account01() {}
    public Account01(long balance) { this.balance = balance; }
    public Long getId() { return id; }
    public long getBalance() { return balance; }
    public void setBalance(long b) { this.balance = b; }
}
