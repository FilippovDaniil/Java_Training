package m28_junit.practice.task04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Класс под тестом (готов)
class BankAccount {
    private double balance;

    BankAccount(double balance) { this.balance = balance; }

    double getBalance() { return balance; }

    void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        if (amount > balance) {
            throw new IllegalStateException("Недостаточно средств");
        }
        balance -= amount;
    }
}
