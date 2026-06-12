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

public class Task06 {

    static final String URL  = "jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            // Создать таблицу
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS accounts " +
                           "(id BIGINT PRIMARY KEY, owner VARCHAR(100), balance BIGINT)");
            }

            AccountDao dao = new JdbcAccountDao(con);

            // Сохранить счёт
            dao.save(new Account(1L, "Алиса", 5000L));

            // Найти и вывести
            Account found = dao.findById(1L);
            System.out.println("Найден: " + found);

            // Обновить баланс и снова найти
            dao.updateBalance(1L, 7500L);
            System.out.println("После обновления: " + dao.findById(1L));
        }
    }
}
