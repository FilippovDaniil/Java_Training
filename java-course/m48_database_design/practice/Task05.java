package m48_database_design.practice;

/**
 * Задача 05 — Модуль 48: Приведение к 3НФ
 *
 * ДАНО (во 2НФ, но не в 3НФ):
 *   employees(id, name, dept_id, dept_name, dept_location)
 *   Проблема: dept_name и dept_location зависят от dept_id
 *   (неключевого атрибута) → транзитивная зависимость.
 *
 * ЗАДАНИЕ:
 *   1) объясните в комментарии транзитивную зависимость;
 *   2) приведите к 3НФ: вынесите атрибуты отдела в таблицу
 *      departments(dept_id, dept_name, dept_location);
 *   3) напишите CREATE TABLE для результата (employees + departments).
 *
 * ПОДСКАЗКА:
 *   После нормализации employees хранит только dept_id (FK),
 *   а название/локация отдела — в departments.
 */
public class Task05 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПРИВЕДЕНИЕ К 3НФ (ТРЕТЬЯ НОРМАЛЬНАЯ ФОРМА)
            -- ============================================
            
            /*
            ================================================
            ЧАСТЬ 1: АНАЛИЗ НАРУШЕНИЯ 3НФ
            ================================================
            
            ИСХОДНАЯ ТАБЛИЦА (ВО 2НФ, НО НЕ В 3НФ):
            employees(id, name, dept_id, dept_name, dept_location)
            
            ПЕРВИЧНЫЙ КЛЮЧ: id
            
            ЗАВИСИМОСТИ:
            id → name, dept_id, dept_name, dept_location
            dept_id → dept_name, dept_location
            
            ТРАНЗИТИВНАЯ ЗАВИСИМОСТЬ:
            id → dept_id → dept_name, dept_location
            
            Это означает, что dept_name и dept_location зависят от dept_id,
            который не является первичным ключом (транзитивная зависимость).
            
            ДАННЫЕ (ПРИМЕР):
            id | name       | dept_id | dept_name | dept_location
            ---|------------|---------|-----------|---------------
            1  | Иван       | 101     | IT        | Москва
            2  | Мария      | 101     | IT        | Москва
            3  | Петр       | 102     | HR        | Санкт-Петербург
            4  | Анна       | 103     | Finance   | Москва
            
            ПРОБЛЕМЫ:
            1. Избыточность: название и локация отдела повторяются
            2. Аномалии обновления: нужно обновлять название отдела во всех строках
            3. Аномалии вставки: нельзя добавить отдел без сотрудника
            4. Аномалии удаления: при удалении последнего сотрудника теряется информация об отделе
            
            ОПРЕДЕЛЕНИЕ 3НФ:
            Таблица находится в 3НФ, если:
            1. Она уже во 2НФ
            2. Все неключевые атрибуты зависят ТОЛЬКО от первичного ключа
               (нет транзитивных зависимостей)
            */
            
            /*
            ================================================
            ЧАСТЬ 2: ПРИВЕДЕНИЕ К 3НФ (РЕШЕНИЕ)
            ================================================
            
            СПОСОБ РЕШЕНИЯ:
            1. Создать отдельную таблицу departments для данных об отделах
            2. В employees оставить только id, name, dept_id
            3. Установить внешний ключ на departments.dept_id
            
            РЕЗУЛЬТИРУЮЩИЕ ТАБЛИЦЫ:
            
            1. departments (dept_id, dept_name, dept_location)
               - PK: dept_id
               - Все атрибуты зависят от dept_id
            
            2. employees (id, name, dept_id)
               - PK: id
               - FK: dept_id -> departments.dept_id
               - Все атрибуты зависят от id (первичного ключа)
            
            ПРЕИМУЩЕСТВА:
            - Нет дублирования названий и локаций отделов
            - Легко обновлять информацию об отделах (одно место)
            - Можно добавлять отделы без сотрудников
            - Можно удалять сотрудников без потери информации об отделах
            - Соответствие 3НФ
            */
            
            -- ============================================
            -- ЧАСТЬ 3: CREATE TABLE (3НФ)
            -- ============================================
            
            -- 1. Таблица отделов (вынесенные атрибуты)
            CREATE TABLE IF NOT EXISTS departments (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                name        VARCHAR(100) NOT NULL,
                location    VARCHAR(100),
                manager     VARCHAR(100),
                phone       VARCHAR(20),
                budget      DECIMAL(12, 2) DEFAULT 0 CHECK (budget >= 0),
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                INDEX idx_name (name),
                INDEX idx_location (location)
            );
            
            -- 2. Таблица сотрудников (3НФ)
            CREATE TABLE IF NOT EXISTS employees (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                name        VARCHAR(100) NOT NULL,
                dept_id     INT,
                email       VARCHAR(100) UNIQUE,
                phone       VARCHAR(20),
                hire_date   DATE,
                salary      DECIMAL(10, 2) CHECK (salary >= 0),
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                -- Внешний ключ на отделы
                FOREIGN KEY (dept_id) REFERENCES departments(id)
                    ON DELETE SET NULL 
                    ON UPDATE CASCADE,
                
                INDEX idx_name (name),
                INDEX idx_dept (dept_id),
                INDEX idx_email (email)
            );
            
            -- ============================================
            -- ДОПОЛНИТЕЛЬНО: ИНДЕКСЫ
            -- ============================================
            CREATE INDEX idx_employees_dept ON employees(dept_id);
            CREATE INDEX idx_departments_name ON departments(name);
            
            -- ============================================
            -- ПРИМЕР ЗАПОЛНЕНИЯ ДАННЫМИ
            -- ============================================
            
            -- Вставка отделов
            INSERT INTO departments (id, name, location, manager, budget) VALUES
                (101, 'IT', 'Москва', 'Сергей Иванов', 1000000.00),
                (102, 'HR', 'Санкт-Петербург', 'Анна Смирнова', 500000.00),
                (103, 'Finance', 'Москва', 'Петр Сидоров', 750000.00),
                (104, 'Marketing', 'Казань', 'Елена Петрова', 600000.00);
            
            -- Вставка сотрудников
            INSERT INTO employees (name, dept_id, email, phone, hire_date, salary) VALUES
                ('Иван Петров', 101, 'ivan@company.com', '+7 999 111-22-33', '2020-03-15', 80000.00),
                ('Мария Смирнова', 101, 'maria@company.com', '+7 999 222-33-44', '2019-07-22', 90000.00),
                ('Петр Сидоров', 102, 'petr@company.com', '+7 999 333-44-55', '2021-01-10', 70000.00),
                ('Анна Кузнецова', 103, 'anna@company.com', '+7 999 444-55-66', '2020-11-01', 85000.00),
                ('Сергей Иванов', 103, 'sergey@company.com', '+7 999 555-66-77', '2018-05-20', 95000.00),
                ('Елена Петрова', 104, 'elena@company.com', '+7 999 666-77-88', '2022-02-14', 75000.00);
            
            -- ============================================
            -- ПРОВЕРКА 3НФ
            -- ============================================
            
            -- 1. Проверка структуры
            SELECT '=== Отделы ===' AS info;
            SELECT * FROM departments ORDER BY id;
            
            SELECT '=== Сотрудники ===' AS info;
            SELECT * FROM employees ORDER BY id;
            
            -- 2. Запрос с JOIN (получение полной информации)
            SELECT '=== Сотрудники с информацией об отделах ===' AS info;
            SELECT 
                e.id,
                e.name AS employee_name,
                d.name AS department_name,
                d.location AS department_location,
                e.salary,
                e.hire_date
            FROM employees e
            LEFT JOIN departments d ON e.dept_id = d.id
            ORDER BY d.name, e.name;
            
            -- 3. Проверка отсутствия транзитивных зависимостей
            SELECT '=== Проверка: нет транзитивных зависимостей ===' AS info;
            SELECT 'В employees есть только: id, name, dept_id (FK)' AS message;
            SELECT 'Нет dept_name и dept_location - они вынесены в departments' AS message;
            
            -- ============================================
            -- СРАВНЕНИЕ ДО И ПОСЛЕ 3НФ
            -- ============================================
            
            /*
            ДО 3НФ (employees):
            ┌────┬────────────┬─────────┬───────────┬─────────────────┐
            │ id │ name       │ dept_id │ dept_name │ dept_location   │
            ├────┼────────────┼─────────┼───────────┼─────────────────┤
            │ 1  │ Иван       │ 101     │ IT        │ Москва          │
            │ 2  │ Мария      │ 101     │ IT        │ Москва          │
            │ 3  │ Петр       │ 102     │ HR        │ Санкт-Петербург │
            │ 4  │ Анна       │ 103     │ Finance   │ Москва          │
            └────┴────────────┴─────────┴───────────┴─────────────────┘
            ПРОБЛЕМЫ: повторяются названия и локации отделов
            
            ПОСЛЕ 3НФ:
            
            departments:
            ┌─────────┬───────────┬─────────────────┬───────────────┐
            │ dept_id │ name      │ location        │ manager       │
            ├─────────┼───────────┼─────────────────┼───────────────┤
            │ 101     │ IT        │ Москва          │ Сергей Иванов │
            │ 102     │ HR        │ Санкт-Петербург │ Анна Смирнова │
            │ 103     │ Finance   │ Москва          │ Петр Сидоров  │
            └─────────┴───────────┴─────────────────┴───────────────┘
            
            employees:
            ┌────┬────────────┬─────────┬──────────────┐
            │ id │ name       │ dept_id │ salary       │
            ├────┼────────────┼─────────┼──────────────┤
            │ 1  │ Иван       │ 101     │ 80000.00     │
            │ 2  │ Мария      │ 101     │ 90000.00     │
            │ 3  │ Петр       │ 102     │ 70000.00     │
            │ 4  │ Анна       │ 103     │ 85000.00     │
            └────┴────────────┴─────────┴──────────────┘
            
            ПРЕИМУЩЕСТВА:
            ✅ Нет дублирования названий и локаций отделов
            ✅ Легко обновлять информацию об отделах
            ✅ Можно добавлять отделы без сотрудников
            ✅ Можно удалять сотрудников без потери информации об отделах
            ✅ Соответствие 3НФ
            */
            
            -- ============================================
            -- ДОПОЛНИТЕЛЬНЫЕ ЗАПРОСЫ
            -- ============================================
            
            -- 1. Статистика по отделам
            SELECT '=== Статистика по отделам ===' AS info;
            SELECT 
                d.id AS dept_id,
                d.name AS department,
                d.location,
                COUNT(e.id) AS employee_count,
                ROUND(AVG(e.salary), 2) AS avg_salary,
                MIN(e.salary) AS min_salary,
                MAX(e.salary) AS max_salary,
                ROUND(SUM(e.salary), 2) AS total_salary
            FROM departments d
            LEFT JOIN employees e ON d.id = e.dept_id
            GROUP BY d.id, d.name, d.location
            ORDER BY employee_count DESC;
            
            -- 2. Отделы без сотрудников
            SELECT '=== Отделы без сотрудников ===' AS info;
            SELECT 
                d.name AS department,
                d.location
            FROM departments d
            LEFT JOIN employees e ON d.id = e.dept_id
            WHERE e.id IS NULL
            ORDER BY d.name;
            
            -- 3. Сотрудники без отдела
            SELECT '=== Сотрудники без отдела ===' AS info;
            SELECT 
                e.name AS employee,
                e.salary,
                e.hire_date
            FROM employees e
            WHERE e.dept_id IS NULL
            ORDER BY e.name;
            """;
        System.out.println(sql);
    }
}
