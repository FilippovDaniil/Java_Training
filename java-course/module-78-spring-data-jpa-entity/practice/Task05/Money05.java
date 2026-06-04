import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: @Embeddable
class Money05 {
    private long amount;
    private String currency;

    protected Money05() {}
    public Money05(long amount, String currency) { this.amount = amount; this.currency = currency; }
}
