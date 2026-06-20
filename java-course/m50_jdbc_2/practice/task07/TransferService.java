package m50_jdbc_2.practice.task07;

import java.sql.Connection;
import java.sql.SQLException;

public class TransferService {

    private final Connection con;
    private final AccountDao2 dao;

    TransferService(Connection con, AccountDao2 dao) {
        this.con = con;
        this.dao = dao;
    }

    void transfer(long fromId, long toId, long amount) throws Exception {
        // Валидация
        validateTransfer(fromId, toId, amount);

        // Получаем счета
        Account2 from = getAccountOrThrow(fromId, "Отправитель");
        Account2 to = getAccountOrThrow(toId, "Получатель");

        // Проверка баланса
        checkSufficientBalance(from, amount);

        // Выполняем перевод
        executeTransfer(from, to, amount);
    }

    private void validateTransfer(long fromId, long toId, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма перевода должна быть положительной");
        }
        if (fromId == toId) {
            throw new IllegalArgumentException("Нельзя перевести деньги самому себе");
        }
    }

    private Account2 getAccountOrThrow(long id, String role) throws SQLException {
        Account2 account = dao.findById(id);
        if (account == null) {
            throw new IllegalArgumentException(role + " с ID " + id + " не найден");
        }
        return account;
    }

    private void checkSufficientBalance(Account2 account, long amount) {
        if (account.balance < amount) {
            throw new IllegalStateException(
                    String.format("Недостаточно средств: %d < %d", account.balance, amount)
            );
        }
    }

    private void executeTransfer(Account2 from, Account2 to, long amount) throws Exception {
        boolean autoCommit = con.getAutoCommit();
        try {
            con.setAutoCommit(false);
            logTransfer(from, to, amount);

            dao.updateBalance(from.id, from.balance - amount);
            dao.updateBalance(to.id, to.balance + amount);

            con.commit();
            System.out.println("   ✅ Транзакция успешно завершена");

        } catch (Exception e) {
            con.rollback();
            System.err.println("   ❌ Транзакция откачена: " + e.getMessage());
            throw e;
        } finally {
            con.setAutoCommit(autoCommit);
        }
    }

    private void logTransfer(Account2 from, Account2 to, long amount) {
        System.out.printf("   Перевод %d от %s к %s%n", amount, from.owner, to.owner);
        System.out.printf("   Баланс до: %s=%d, %s=%d%n",
                from.owner, from.balance, to.owner, to.balance);
    }
}
