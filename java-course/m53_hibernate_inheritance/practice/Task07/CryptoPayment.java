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
// TODO: @Table(name = "crypto_payments") — только если JOINED
// TODO: @DiscriminatorValue("CRYPTO") — только если SINGLE_TABLE
class CryptoPayment extends Payment {
    @Column(name = "wallet_address")
    private String walletAddress;

    @Column(name = "crypto_currency")
    private String cryptoCurrency; // BTC, ETH, USDT, ...

    @Column(name = "transaction_hash")
    private String transactionHash; // заполняется при успехе

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate; // курс крипты к RUB на момент оплаты

    // TODO: конструктор(amount, walletAddress, cryptoCurrency, exchangeRate), геттеры, toString()
}
