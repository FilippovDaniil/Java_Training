package m45_sql_dml.practice;

/**
 * Задача 01 — Модуль 45: Варианты INSERT
 *
 * ДАНО (создайте перед началом):
 *   CREATE TABLE employees (
 *       id INT PRIMARY KEY AUTO_INCREMENT,
 *       name VARCHAR(100) NOT NULL,
 *       department VARCHAR(50),
 *       salary DECIMAL(10,2),
 *       hired DATE
 *   );
 *
 * ЗАДАНИЕ (SQL):
 *   1) одиночный INSERT с указанием всех столбцов;
 *   2) множественный INSERT (3+ сотрудника одним запросом);
 *   3) INSERT только в часть столбцов (name + department),
 *      остальные останутся NULL/по умолчанию.
 *
 * ПОДСКАЗКА:
 *   INSERT INTO employees (name, department, salary, hired)
 *   VALUES ('Иван', 'IT', 80000, '2020-03-15');
 */
public class Task01 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦЫ (если ещё не создана)
            -- ============================================
            CREATE TABLE IF NOT EXISTS employees (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                name       VARCHAR(100) NOT NULL,
                department VARCHAR(50),
                salary     DECIMAL(10,2),
                hired      DATE
            );
            
            -- ============================================
            -- 1) ОДИНОЧНЫЙ INSERT (все столбцы)
            -- ============================================
            INSERT INTO employees (name, department, salary, hired)
            VALUES ('Иван Петров', 'IT', 80000.00, '2020-03-15');
            
            -- ============================================
            -- 2) МНОЖЕСТВЕННЫЙ INSERT (3+ сотрудника)
            -- ============================================
            INSERT INTO employees (name, department, salary, hired) VALUES
                ('Мария Смирнова', 'HR', 65000.00, '2019-07-22'),
                ('Петр Сидоров', 'IT', 95000.00, '2018-11-01'),
                ('Анна Кузнецова', 'Finance', 72000.00, '2021-02-10'),
                ('Сергей Иванов', 'Marketing', 68000.00, '2020-09-14'),
                ('Елена Петрова', 'IT', 85000.00, '2019-05-30');
            
            -- ============================================
            -- 3) INSERT ТОЛЬКО В ЧАСТЬ СТОЛБЦОВ
            -- ============================================
            -- salary и hired останутся NULL
            INSERT INTO employees (name, department)
            VALUES 
                ('Алексей Васильев', 'Sales'),
                ('Ольга Павлова', 'IT'),
                ('Дмитрий Новиков', 'Finance');
            
            -- ============================================
            -- ПРОВЕРКА РЕЗУЛЬТАТОВ
            -- ============================================
            SELECT * FROM employees ORDER BY id;
            
            -- ============================================
            -- ДОПОЛНИТЕЛЬНЫЕ ВАРИАНТЫ INSERT
            -- ============================================
            
            -- 4) INSERT с использованием DEFAULT
            INSERT INTO employees (name, department, salary, hired)
            VALUES ('Николай Соколов', 'IT', DEFAULT, DEFAULT);
            
            -- 5) INSERT из SELECT (копирование данных)
            -- INSERT INTO employees (name, department, salary, hired)
            -- SELECT name, department, salary, hired FROM temp_employees;
            
            -- 6) INSERT IGNORE (пропуск дубликатов) - для MySQL
            -- INSERT IGNORE INTO employees (name, department) VALUES
            --     ('Иван Петров', 'IT'); -- будет пропущен, если есть дубликат по уникальному ключу
            """;
        System.out.println(sql);
    }
}
