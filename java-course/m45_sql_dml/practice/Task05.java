package m45_sql_dml.practice;

/**
 * Задача 05 — Модуль 45: UPDATE
 *
 * ЗАДАНИЕ (SQL) для таблицы employees:
 *   1) повысьте зарплату конкретному сотруднику (по id) до 95000;
 *   2) поднимите зарплату всем сотрудникам отдела 'IT' на 10%
 *      (salary = salary * 1.1);
 *   3) переведите сотрудников без отдела (department IS NULL) в отдел
 *      'Общий', одновременно установив зарплату 40000.
 *
 * ВНИМАНИЕ:
 *   Каждый UPDATE — с WHERE! Перед изменением проверьте тем же WHERE
 *   через SELECT, какие строки будут затронуты.
 *
 * ПОДСКАЗКА:
 *   UPDATE employees SET salary = salary * 1.1 WHERE department = 'IT';
 */
public class Task05 {
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
                ('Елена Петрова', NULL, 55000.00, '2019-05-30'),
                ('Алексей Васильев', 'Sales', 70000.00, '2021-06-01'),
                ('Ольга Павлова', 'IT', 90000.00, '2018-03-20'),
                ('Дмитрий Новиков', 'HR', 58000.00, '2022-01-15'),
                ('Наталья Соколова', NULL, 82000.00, '2019-11-10');
            
            -- ============================================
            -- ПРОВЕРКА ДО ИЗМЕНЕНИЙ
            -- ============================================
            SELECT '=== ДО ИЗМЕНЕНИЙ ===' AS info;
            SELECT * FROM employees ORDER BY id;
            
            -- ============================================
            -- 1) ПОВЫСЬТЕ ЗАРПЛАТУ КОНКРЕТНОМУ СОТРУДНИКУ (ПО ID)
            -- ============================================
            -- Проверка перед UPDATE
            SELECT '1) Проверка сотрудника с id=3' AS info;
            SELECT * FROM employees WHERE id = 3;
            
            -- UPDATE
            UPDATE employees 
            SET salary = 95000.00 
            WHERE id = 3;
            
            -- Проверка после UPDATE
            SELECT '1) После обновления' AS info;
            SELECT * FROM employees WHERE id = 3;
            
            -- ============================================
            -- 2) ПОДНИМИТЕ ЗАРПЛАТУ ВСЕМ IT НА 10%
            -- ============================================
            -- Проверка перед UPDATE
            SELECT '2) Проверка сотрудников IT до обновления' AS info;
            SELECT * FROM employees WHERE department = 'IT';
            
            -- UPDATE
            UPDATE employees 
            SET salary = salary * 1.1 
            WHERE department = 'IT';
            
            -- Проверка после UPDATE
            SELECT '2) Сотрудники IT после повышения на 10%' AS info;
            SELECT * FROM employees WHERE department = 'IT';
            
            -- ============================================
            -- 3) ПЕРЕВЕДИТЕ СОТРУДНИКОВ БЕЗ ОТДЕЛА В 'Общий'
            -- ============================================
            -- Проверка перед UPDATE
            SELECT '3) Проверка сотрудников без отдела до обновления' AS info;
            SELECT * FROM employees WHERE department IS NULL;
            
            -- UPDATE
            UPDATE employees 
            SET department = 'Общий', salary = 40000.00 
            WHERE department IS NULL;
            
            -- Проверка после UPDATE
            SELECT '3) Сотрудники без отдела после перевода' AS info;
            SELECT * FROM employees WHERE department = 'Общий';
            
            -- ============================================
            -- ФИНАЛЬНАЯ ПРОВЕРКА
            -- ============================================
            SELECT '=== ИТОГОВЫЕ ДАННЫЕ ===' AS info;
            SELECT * FROM employees ORDER BY id;
            """;
        System.out.println(sql);
    }
}