package m45_sql_dml.practice;

/**
 * Задача 04 — Модуль 45: IN, BETWEEN, NULL
 *
 * ЗАДАНИЕ (SQL) для таблицы employees:
 *   1) сотрудники с зарплатой в диапазоне 50000..90000 (BETWEEN);
 *   2) сотрудники отделов 'IT', 'HR', 'Sales' (IN);
 *   3) сотрудники, у которых НЕ указан отдел (IS NULL);
 *   4) сотрудники, у которых отдел указан (IS NOT NULL);
 *   5) сотрудники НЕ из 'IT' (NOT IN или <>).
 *
 * ВНИМАНИЕ:
 *   NULL проверяется ТОЛЬКО через IS NULL / IS NOT NULL,
 *   а не через = NULL.
 *
 * ПОДСКАЗКА:
 *   WHERE salary BETWEEN 50000 AND 90000;
 *   WHERE department IN ('IT','HR','Sales');
 *   WHERE department IS NULL;
 */
public class Task04 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПОДГОТОВКА ТАБЛИЦЫ И ДАННЫХ
            -- ============================================
            CREATE TABLE IF NOT EXISTS employees (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                name       VARCHAR(100) NOT NULL,
                department VARCHAR(50),
                salary     DECIMAL(10,2),
                hired      DATE
            );
            
            INSERT INTO employees (name, department, salary, hired) VALUES
                ('Иван Петров', 'IT', 80000.00, '2020-03-15'),
                ('Мария Смирнова', 'HR', 65000.00, '2019-07-22'),
                ('Петр Сидоров', 'IT', 95000.00, '2018-11-01'),
                ('Анна Кузнецова', 'Finance', 72000.00, '2021-02-10'),
                ('Сергей Иванов', 'Marketing', 68000.00, '2020-09-14'),
                ('Елена Петрова', NULL, 55000.00, '2019-05-30'), -- NULL отдел
                ('Алексей Васильев', 'Sales', 70000.00, '2021-06-01'),
                ('Ольга Павлова', 'IT', 90000.00, '2018-03-20'),
                ('Дмитрий Новиков', 'HR', 58000.00, '2022-01-15'),
                ('Наталья Соколова', 'Finance', 82000.00, '2019-11-10'),
                ('Андрей Морозов', NULL, 75000.00, '2021-08-05'), -- NULL отдел
                ('Александр Волков', 'Marketing', 85000.00, '2020-11-12');
            
            -- ============================================
            -- 1) ЗАРПЛАТА В ДИАПАЗОНЕ 50000..90000 (BETWEEN)
            -- ============================================
            SELECT * 
            FROM employees 
            WHERE salary BETWEEN 50000 AND 90000;
            
            -- ============================================
            -- 2) ОТДЕЛЫ 'IT', 'HR', 'Sales' (IN)
            -- ============================================
            SELECT * 
            FROM employees 
            WHERE department IN ('IT', 'HR', 'Sales');
            
            -- ============================================
            -- 3) НЕ УКАЗАН ОТДЕЛ (IS NULL)
            -- ============================================
            SELECT * 
            FROM employees 
            WHERE department IS NULL;
            
            -- ============================================
            -- 4) УКАЗАН ОТДЕЛ (IS NOT NULL)
            -- ============================================
            SELECT * 
            FROM employees 
            WHERE department IS NOT NULL;
            
            -- ============================================
            -- 5) СОТРУДНИКИ НЕ ИЗ 'IT' (NOT IN или <>)
            -- ============================================
            -- Вариант с NOT IN
            SELECT * 
            FROM employees 
            WHERE department NOT IN ('IT');
            
            -- Вариант с <>
            -- SELECT * FROM employees WHERE department <> 'IT';
            """;
        System.out.println(sql);
    }
}