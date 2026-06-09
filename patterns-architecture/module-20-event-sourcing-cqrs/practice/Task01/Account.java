import java.util.List;

// Состояние восстанавливается из лога событий (Event Sourcing).
class Account {
    private long balance = 0;

    public void apply(Object event) {
        // TODO: if (event instanceof MoneyDeposited e) balance += e.amount();
        //       else if (event instanceof MoneyWithdrawn e) balance -= e.amount();
    }

    public static Account replay(List<Object> events) {
        // TODO: создать Account и применить все события по порядку
        return new Account();
    }

    public long balance() {
        // TODO: вернуть balance
        return balance;
    }
}
