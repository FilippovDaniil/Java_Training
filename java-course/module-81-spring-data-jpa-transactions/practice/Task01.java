/**
 * Задача 01 — Модуль 81: @Transactional на сервисном методе
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) AccountService01.transfer(fromId, toId, amount) пометьте @Transactional.
 *      Внутри: снять amount с одного счёта, добавить другому, сохранить оба.
 *      Если на счёте-источнике не хватает средств — throw new IllegalStateException(...)
 *      (тогда вся операция откатится).
 *   2) В CommandLineRunner: создайте два счёта, выполните успешный перевод,
 *      затем неуспешный (слишком большой) — поймайте исключение и проверьте,
 *      что балансы НЕ изменились (откат).
 *
 * ЦЕЛЬ: убедиться, что @Transactional делает операцию атомарной (всё или ничего).
 *
 * ПОДСКАЗКА: @Transactional ставят на СЕРВИС, не на репозиторий.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

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

interface AccountRepository01 extends JpaRepository<Account01, Long> {}

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
