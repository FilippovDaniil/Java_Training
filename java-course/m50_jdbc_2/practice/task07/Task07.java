package m50_jdbc_2.practice.task07;

/**
 * Задача 07 — Модуль 50: МИНИ-ПРОЕКТ — сервис переводов TransferService
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   Реализуйте TransferService.transfer(long fromId, long toId, long amount):
 *     1) Найдите счёт-источник через dao.findById(fromId).
 *        Если не найден — бросьте IllegalArgumentException("Счёт не найден: " + fromId).
 *     2) Найдите счёт-получатель через dao.findById(toId).
 *        Если не найден — бросьте IllegalArgumentException("Счёт не найден: " + toId).
 *     3) Если баланс источника меньше amount — бросьте
 *        IllegalStateException("Недостаточно средств: " + fromBalance + " < " + amount).
 *     4) Всё в одной транзакции:
 *        a) con.setAutoCommit(false)
 *        b) dao.updateBalance(fromId, fromBalance - amount)
 *        c) dao.updateBalance(toId,   toBalance   + amount)
 *        d) con.commit()
 *        e) в catch (Exception) -> con.rollback(), throw e (перебросить)
 *   В main() создайте три счёта, выполните успешный перевод и перевод
 *   с недостаточным балансом (перехватите исключение), выведите итоговые балансы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Перевод 1500 от Алисы к Бобу...
 *   Успешно переведено.
 *   Перевод 9999 от Боба к Сергею (недостаточно средств)...
 *   Ошибка: Недостаточно средств: 2500 < 9999
 *   Итоговые балансы:
 *     Алиса   = 3500
 *     Боб     = 2500
 *     Сергей  = 1000
 *
 * ПОДСКАЗКА:
 *   - TransferService получает Connection и AccountDao2 в конструктор.
 *   - Все проверки (счёт существует, баланс достаточен) делать ДО начала транзакции.
 *   - rollback вызывать только если setAutoCommit(false) уже был вызван
 *     (используйте флаг или finally с проверкой con.getAutoCommit()).
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task07 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("=== СЕРВИС ПЕРЕВОДОВ ===\n");

            // Инициализация
            setup(con);

            // Выводим начальные балансы
            System.out.println("Начальные балансы:");
            printBalances(con);
            System.out.println();

            AccountDao2     dao = new JdbcAccountDao2(con);
            TransferService svc = new TransferService(con, dao);

            // 1. Успешный перевод
            System.out.println("--- 1. УСПЕШНЫЙ ПЕРЕВОД ---");
            System.out.println("Перевод 1500 от Алисы к Бобу...");
            try {
                svc.transfer(1, 2, 1500);
                System.out.println("   ✅ Успешно переведено.");
            } catch (Exception e) {
                System.out.println("   ❌ Ошибка: " + e.getMessage());
            }

            // 2. Перевод с недостаточным балансом
            System.out.println("\n--- 2. НЕДОСТАТОЧНО СРЕДСТВ ---");
            System.out.println("Перевод 9999 от Боба к Сергею (недостаточно средств)...");
            try {
                svc.transfer(2, 3, 9999);
                System.out.println("   ✅ Успешно переведено.");
            } catch (Exception e) {
                System.out.println("   ❌ Ошибка: " + e.getMessage());
            }

            // 3. Перевод на несуществующий счет
            System.out.println("\n--- 3. НЕСУЩЕСТВУЮЩИЙ СЧЕТ ---");
            System.out.println("Перевод 100 от Алисы к ID=99 (не существует)...");
            try {
                svc.transfer(1, 99, 100);
                System.out.println("   ✅ Успешно переведено.");
            } catch (Exception e) {
                System.out.println("   ❌ Ошибка: " + e.getMessage());
            }

            // 4. Перевод с отрицательной суммой
            System.out.println("\n--- 4. ОТРИЦАТЕЛЬНАЯ СУММА ---");
            System.out.println("Перевод -500 от Алисы к Бобу...");
            try {
                svc.transfer(1, 2, -500);
                System.out.println("   ✅ Успешно переведено.");
            } catch (Exception e) {
                System.out.println("   ❌ Ошибка: " + e.getMessage());
            }

            // 5. Перевод самому себе
            System.out.println("\n--- 5. ПЕРЕВОД САМОМУ СЕБЕ ---");
            System.out.println("Перевод 100 от Алисы к Алисе...");
            try {
                svc.transfer(1, 1, 100);
                System.out.println("   ✅ Успешно переведено.");
            } catch (Exception e) {
                System.out.println("   ❌ Ошибка: " + e.getMessage());
            }

            // Итоговые балансы
            System.out.println("\n=== ИТОГОВЫЕ БАЛАНСЫ ===");
            printBalances(con);

        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение закрыто");
    }

    // --- TransferService (реализован) ---

    static class TransferService {
        private final Connection  con;
        private final AccountDao2 dao;

        TransferService(Connection con, AccountDao2 dao) {
            this.con = con;
            this.dao = dao;
        }

        /**
         * Переводит amount со счёта fromId на счёт toId атомарно (транзакция).
         * Бросает IllegalArgumentException если счёт не найден.
         * Бросает IllegalStateException если недостаточно средств.
         */
        void transfer(long fromId, long toId, long amount) throws Exception {
            // 1. Проверка валидности суммы
            if (amount <= 0) {
                throw new IllegalArgumentException(
                        "Сумма перевода должна быть положительной: " + amount
                );
            }

            // 2. Проверка перевода самому себе
            if (fromId == toId) {
                throw new IllegalArgumentException(
                        "Нельзя перевести деньги самому себе"
                );
            }

            // 3. Находим счёт отправителя
            Account2 fromAccount = dao.findById(fromId);
            if (fromAccount == null) {
                throw new IllegalArgumentException(
                        "Счёт отправителя не найден: " + fromId
                );
            }

            // 4. Находим счёт получателя
            Account2 toAccount = dao.findById(toId);
            if (toAccount == null) {
                throw new IllegalArgumentException(
                        "Счёт получателя не найден: " + toId
                );
            }

            // 5. Проверяем достаточность средств
            if (fromAccount.balance < amount) {
                throw new IllegalStateException(
                        String.format("Недостаточно средств: %d < %d",
                                fromAccount.balance, amount)
                );
            }

            System.out.printf("   Отправитель: %s (баланс: %d)%n",
                    fromAccount.owner, fromAccount.balance);
            System.out.printf("   Получатель: %s (баланс: %d)%n",
                    toAccount.owner, toAccount.balance);
            System.out.printf("   Сумма перевода: %d%n", amount);

            // 6. Выполняем перевод в транзакции
            boolean autoCommitWasEnabled = con.getAutoCommit();
            try {
                con.setAutoCommit(false);
                System.out.println("   🔄 Транзакция начата");

                // Обновляем баланс отправителя
                dao.updateBalance(fromId, fromAccount.balance - amount);
                System.out.printf("   📤 Списанo %d со счета %s%n",
                        amount, fromAccount.owner);

                // Обновляем баланс получателя
                dao.updateBalance(toId, toAccount.balance + amount);
                System.out.printf("   📥 Зачислено %d на счет %s%n",
                        amount, toAccount.owner);

                // Фиксируем транзакцию
                con.commit();
                System.out.println("   ✅ Транзакция зафиксирована");

            } catch (Exception e) {
                // Откатываем транзакцию при ошибке
                System.out.println("   ❌ Ошибка: " + e.getMessage());
                try {
                    con.rollback();
                    System.out.println("   🔄 Транзакция откачена");
                } catch (SQLException rollbackEx) {
                    System.err.println("   ❌ Ошибка при rollback: " + rollbackEx.getMessage());
                }
                throw e;
            } finally {
                // Восстанавливаем исходное состояние auto-commit
                try {
                    con.setAutoCommit(autoCommitWasEnabled);
                } catch (SQLException e) {
                    System.err.println("   ❌ Ошибка восстановления auto-commit: " + e.getMessage());
                }
            }
        }
    }

    // --- Вспомогательные методы (готовы, не менять) ---

    static void setup(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS accounts (
                    id      BIGINT PRIMARY KEY,
                    owner   VARCHAR(100),
                    balance BIGINT
                )
            """);
            st.execute("DELETE FROM accounts");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (1, 'Алиса', 5000)");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (2, 'Боб', 1000)");
            st.execute("INSERT INTO accounts (id, owner, balance) VALUES (3, 'Сергей', 1000)");
        }
    }

    static void printBalances(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT owner, balance FROM accounts ORDER BY id")) {
            System.out.println("Владелец | Баланс");
            System.out.println("---------|--------");
            while (rs.next()) {
                System.out.printf("  %-8s | %d%n",
                        rs.getString("owner"), rs.getLong("balance"));
            }
        }
    }
}