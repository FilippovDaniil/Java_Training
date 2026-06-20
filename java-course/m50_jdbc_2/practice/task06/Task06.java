package m50_jdbc_2.practice.task06;

/**
 * Задача 06 — Модуль 50: паттерн DAO
 *
 * ДЛЯ ЗАПУСКА: H2 (com.h2database:h2), URL jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс JdbcAccountDao, который имплементирует интерфейс AccountDao.
 *   Методы для реализации:
 *     - findById(long id)      — SELECT по id, вернуть Account или null если не найден.
 *     - save(Account account)  — INSERT строки в таблицу accounts.
 *     - updateBalance(long id, long newBalance) — UPDATE balance WHERE id = ?
 *   Используйте PreparedStatement с параметрами (?), не строковую конкатенацию.
 *   В main() создайте таблицу, создайте AccountDao, сохраните счёт,
 *   найдите его по id, обновите баланс, найдите снова — выведите на экран.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Найден: Account[id=1, owner=Алиса, balance=5000]
 *   После обновления: Account[id=1, owner=Алиса, balance=7500]
 *
 * ПОДСКАЗКА:
 *   try (PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
 *       ps.setLong(1, id);
 *       try (ResultSet rs = ps.executeQuery()) {
 *           if (rs.next()) return new Account(rs.getLong("id"), ...);
 *       }
 *   }
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Task06 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("=== РАСШИРЕННЫЙ ДЕМОНСТРАЦИЯ DAO ===\n");

            // Подготовка таблицы
            try (Statement st = con.createStatement()) {
                st.execute("""
                    CREATE TABLE IF NOT EXISTS accounts (
                        id      BIGINT PRIMARY KEY,
                        owner   VARCHAR(100),
                        balance BIGINT
                    )
                """);
                st.execute("DELETE FROM accounts");
            }

            // Создаем расширенный DAO
            JdbcAccountDao dao = new JdbcAccountDao(con);

            // 1. Сохранение нескольких счетов
            System.out.println("--- 1. СОХРАНЕНИЕ СЧЕТОВ ---");
            Account[] accounts = {
                    new Account(1L, "Алиса", 5000L),
                    new Account(2L, "Боб", 3000L),
                    new Account(3L, "Чарли", 7000L)
            };

            for (Account acc : accounts) {
                dao.save(acc);
                System.out.println("   Сохранен: " + acc);
            }

            // 2. Поиск всех счетов
            System.out.println("\n--- 2. ВСЕ СЧЕТА ---");
            List<Account> allAccounts = dao.findAll();
            for (Account acc : allAccounts) {
                System.out.println("   " + acc);
            }

            // 3. Поиск по владельцу
            System.out.println("\n--- 3. ПОИСК ПО ВЛАДЕЛЬЦУ ---");
            Account found = dao.findByOwner("Алиса");
            if (found != null) {
                System.out.println("   Найден: " + found);
            }

            // 4. Поиск по балансу (больше 4000)
            System.out.println("\n--- 4. СЧЕТА С БАЛАНСОМ > 4000 ---");
            List<Account> highBalance = dao.findByBalanceGreaterThan(4000L);
            for (Account acc : highBalance) {
                System.out.println("   " + acc);
            }

            // 5. Обновление с проверкой существования
            System.out.println("\n--- 5. ОБНОВЛЕНИЕ С ПРОВЕРКОЙ ---");
            boolean updated = dao.updateBalanceSafe(1L, 8000L);
            System.out.println("   Обновление ID=1: " + (updated ? "успешно" : "не найдено"));

            updated = dao.updateBalanceSafe(999L, 1000L);
            System.out.println("   Обновление ID=999: " + (updated ? "успешно" : "не найдено"));

            // 6. Удаление счета
            System.out.println("\n--- 6. УДАЛЕНИЕ СЧЕТА (ID=3) ---");
            boolean deleted = dao.delete(3L);
            System.out.println("   Удаление: " + (deleted ? "успешно" : "не найдено"));

            // 7. Проверка существования
            System.out.println("\n--- 7. ПРОВЕРКА СУЩЕСТВОВАНИЯ ---");
            System.out.println("   ID=1 существует: " + dao.exists(1L));
            System.out.println("   ID=3 существует: " + dao.exists(3L));

            // 8. Финальный список
            System.out.println("\n--- 8. ФИНАЛЬНЫЙ СПИСОК ---");
            for (Account acc : dao.findAll()) {
                System.out.println("   " + acc);
            }

            // 9. Статистика
            System.out.println("\n--- 9. СТАТИСТИКА ---");
            AccountStats stats = dao.getStats();
            System.out.printf("   Всего счетов: %d%n", stats.count());
            System.out.printf("   Средний баланс: %d%n", stats.avgBalance());
            System.out.printf("   Минимальный баланс: %d%n", stats.minBalance());
            System.out.printf("   Максимальный баланс: %d%n", stats.maxBalance());
            System.out.printf("   Общий баланс: %d%n", stats.totalBalance());

        } catch (SQLException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Соединение закрыто");
    }
}
