package m44_databases_intro.practice;

/**
 * Задача 05 — Модуль 44: Ограничения (constraints)
 *
 * ЗАДАНИЕ (SQL):
 *   Создайте таблицу students с продуманными ограничениями:
 *     - id      — PK, автонумерация;
 *     - email   — UNIQUE, NOT NULL;
 *     - name    — NOT NULL;
 *     - gpa     — DECIMAL(3,2), с CHECK-ограничением 0.0..5.0;
 *     - group_name — VARCHAR(20), значение по умолчанию 'не назначена'.
 *   Затем попробуйте вставить строку, нарушающую UNIQUE или CHECK,
 *   и опишите в комментарии, что произойдёт.
 *
 * ПОДСКАЗКА:
 *   gpa DECIMAL(3,2) CHECK (gpa >= 0 AND gpa <= 5),
 *   group_name VARCHAR(20) DEFAULT 'не назначена'
 */
public class Task05 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- СОЗДАНИЕ ТАБЛИЦЫ students С ОГРАНИЧЕНИЯМИ
            -- ============================================
            
            -- 1. Создание таблицы с ограничениями
            CREATE TABLE IF NOT EXISTS students (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                email      VARCHAR(100) UNIQUE NOT NULL,
                name       VARCHAR(100) NOT NULL,
                gpa        DECIMAL(3,2) CHECK (gpa >= 0.0 AND gpa <= 5.0),
                group_name VARCHAR(20) DEFAULT 'не назначена'
            );
            
            -- 2. Проверка структуры таблицы
            DESCRIBE students;
            
            -- 3. Вставка корректных данных
            INSERT INTO students (email, name, gpa, group_name) VALUES
                ('ivan@mail.com', 'Иван Петров', 4.5, 'Группа А'),
                ('maria@mail.com', 'Мария Смирнова', 4.8, 'Группа Б'),
                ('petr@mail.com', 'Петр Сидоров', 3.2, DEFAULT),
                ('anna@mail.com', 'Анна Кузнецова', 4.0, 'Группа А');
            
            -- 4. Проверка данных
            SELECT * FROM students;
            
            -- 5. Попытка вставки с дублирующим email (нарушение UNIQUE)
            -- INSERT INTO students (email, name, gpa) VALUES ('ivan@mail.com', 'Дубликат', 4.0);
            -- ❌ Ошибка: Duplicate entry 'ivan@mail.com' for key 'email'
            
            -- 6. Попытка вставки с gpa вне диапазона (нарушение CHECK)
            -- INSERT INTO students (email, name, gpa) VALUES ('new@mail.com', 'Тест', 5.5);
            -- ❌ Ошибка: Check constraint violation: gpa must be between 0.0 and 5.0
            
            -- 7. Попытка вставки с NULL в email (нарушение NOT NULL)
            -- INSERT INTO students (email, name, gpa) VALUES (NULL, 'Без почты', 4.0);
            -- ❌ Ошибка: Column 'email' cannot be null
            
            -- 8. Попытка вставки с NULL в name (нарушение NOT NULL)
            -- INSERT INTO students (email, name, gpa) VALUES ('test@mail.com', NULL, 4.0);
            -- ❌ Ошибка: Column 'name' cannot be null
            """;
        System.out.println(sql);
    }
}
