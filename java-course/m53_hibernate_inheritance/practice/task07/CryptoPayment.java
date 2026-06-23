package m53_hibernate_inheritance.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// ============================================================
// CryptoPayment (дополните @Entity и уникальными полями)
// ============================================================

@Entity
@DiscriminatorValue("CRYPTO")
class CryptoPayment extends Payment {

    @Column(name = "wallet_address")
    private String walletAddress;

    @Column(name = "crypto_currency")
    private String cryptoCurrency;

    @Column(name = "transaction_hash")
    private String transactionHash;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    public CryptoPayment() {}

    public CryptoPayment(BigDecimal amount, String walletAddress, String cryptoCurrency, BigDecimal exchangeRate) {
        super(amount, "RUB", PaymentStatus.PENDING);
        this.walletAddress = walletAddress;
        this.cryptoCurrency = cryptoCurrency;
        this.exchangeRate = exchangeRate;
    }

    public String getWalletAddress() { return walletAddress; }
    public void setWalletAddress(String walletAddress) { this.walletAddress = walletAddress; }

    public String getCryptoCurrency() { return cryptoCurrency; }
    public void setCryptoCurrency(String cryptoCurrency) { this.cryptoCurrency = cryptoCurrency; }

    public String getTransactionHash() { return transactionHash; }
    public void setTransactionHash(String transactionHash) { this.transactionHash = transactionHash; }

    public BigDecimal getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(BigDecimal exchangeRate) { this.exchangeRate = exchangeRate; }

    @Override
    public String toString() {
        return String.format("CryptoPayment{id=%d, amount=%s, status=%s, currency='%s'}",
                getId(), getAmount(), getStatus(), cryptoCurrency);
    }
}