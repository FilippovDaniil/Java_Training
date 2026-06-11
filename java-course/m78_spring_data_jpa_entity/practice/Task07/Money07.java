package m78_spring_data_jpa_entity.practice.task07;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

// TODO: @Embeddable
class Money07 {
    private long amount;
    private String currency;
    protected Money07() {}
    public Money07(long amount, String currency) { this.amount = amount; this.currency = currency; }
}
