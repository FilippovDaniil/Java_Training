package m48_database_design.practice;

/**
 * Задача 03 — Модуль 48: Приведение к 1НФ
 *
 * ДАНО (ненормализованная таблица):
 *   students(id, name, phones, courses)
 *   где в одной ячейке phones хранится "111, 222",
 *   а в courses — "Математика, Физика, История".
 *
 * ЗАДАНИЕ:
 *   1) объясните в комментарии, почему таблица нарушает 1НФ;
 *   2) приведите её к 1НФ: разбейте многозначные поля так, чтобы
 *      каждое значение было атомарным (отдельные строки/таблицы);
 *   3) напишите CREATE TABLE для результата (1НФ).
 *
 * ПОДСКАЗКА:
 *   Вынесите телефоны и курсы в отдельные таблицы со ссылкой на
 *   student_id — каждая ячейка станет атомарной.
 */
public class Task03 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПРИВЕДЕНИЕ К 1НФ (ПЕРВАЯ НОРМАЛЬНАЯ ФОРМА)
            -- ============================================
            
            /*
            ================================================
            ЧАСТЬ 1: АНАЛИЗ НАРУШЕНИЯ 1НФ
            ================================================
            
            ИСХОДНАЯ ТАБЛИЦА (НЕНОРМАЛИЗОВАННАЯ):
            students(id, name, phones, courses)
            
            ДАННЫЕ:
            id | name    | phones    | courses
            ---|---------|-----------|-------------------------
            1  | Иван    | 111, 222  | Математика, Физика, История
            2  | Мария   | 333, 444  | Физика, Химия
            
            ПОЧЕМУ НАРУШАЕТ 1НФ:
            
            1НФ требует, чтобы:
            - Каждая ячейка содержала атомарное (неделимое) значение
            - Все значения в столбце были одного типа
            - Каждая запись была уникальной
            
            НАРУШЕНИЯ:
            1. В столбце 'phones' хранятся множественные значения (111, 222)
            2. В столбце 'courses' хранятся множественные значения
            3. Ячейки содержат списки, которые можно разделить
            
            ПРОБЛЕМЫ ТАКОЙ СТРУКТУРЫ:
            - Сложно искать студентов с конкретным телефоном
            - Сложно искать студентов по курсу
            - Невозможно нормально отсортировать по телефону/курсу
            - Нарушение атомарности данных
            - Избыточность при обновлении
            - Ограниченная гибкость запросов
            */
            
            /*
            ================================================
            ЧАСТЬ 2: ПРИВЕДЕНИЕ К 1НФ (РЕШЕНИЕ)
            ================================================
            
            СПОСОБ 1: РАЗБИЕНИЕ НА ОТДЕЛЬНЫЕ ТАБЛИЦЫ (рекомендуемый)
            
            students (id, name) — основная таблица
            student_phones (id, student_id, phone) — телефоны
            student_courses (id, student_id, course) — курсы
            
            СПОСОБ 2: ДУБЛИРОВАНИЕ СТРОК (менее предпочтительный)
            
            id | name | phone | course
            1  | Иван | 111   | Математика
            1  | Иван | 111   | Физика
            1  | Иван | 111   | История
            1  | Иван | 222   | Математика
            1  | Иван | 222   | Физика
            1  | Иван | 222   | История
            2  | Мария| 333   | Физика
            2  | Мария| 333   | Химия
            2  | Мария| 444   | Физика
            2  | Мария| 444   | Химия
            (Проблема: избыточность данных)
            
            ВЫБИРАЕМ СПОСОБ 1 (рекомендуемый):
            - Нет дублирования
            - Гибкая структура
            - Легко добавлять новые телефоны/курсы
            - Легко искать по телефонам и курсам
            */
            
            -- ============================================
            -- ЧАСТЬ 3: CREATE TABLE (1НФ)
            -- ============================================
            
            -- 1. Основная таблица студентов
            CREATE TABLE IF NOT EXISTS students (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                name       VARCHAR(100) NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            );
            
            -- 2. Таблица телефонов (атомарные значения)
            CREATE TABLE IF NOT EXISTS student_phones (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                student_id INT NOT NULL,
                phone      VARCHAR(20) NOT NULL,
                type       VARCHAR(20) DEFAULT 'mobile',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (student_id) REFERENCES students(id) 
                    ON DELETE CASCADE 
                    ON UPDATE CASCADE
            );
            
            -- 3. Таблица курсов (атомарные значения)
            CREATE TABLE IF NOT EXISTS student_courses (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                student_id INT NOT NULL,
                course     VARCHAR(100) NOT NULL,
                grade      VARCHAR(5),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (student_id) REFERENCES students(id) 
                    ON DELETE CASCADE 
                    ON UPDATE CASCADE
            );
            
            -- ============================================
            -- ДОПОЛНИТЕЛЬНО: ИНДЕКСЫ ДЛЯ ОПТИМИЗАЦИИ
            -- ============================================
            CREATE INDEX idx_student_phones_student ON student_phones(student_id);
            CREATE INDEX idx_student_phones_phone ON student_phones(phone);
            CREATE INDEX idx_student_courses_student ON student_courses(student_id);
            CREATE INDEX idx_student_courses_course ON student_courses(course);
            
            -- ============================================
            -- ПРИМЕР ЗАПОЛНЕНИЯ ДАННЫМИ
            -- ============================================
            
            -- Вставка студентов
            INSERT INTO students (id, name) VALUES
                (1, 'Иван Петров'),
                (2, 'Мария Смирнова');
            
            -- Вставка телефонов
            INSERT INTO student_phones (student_id, phone, type) VALUES
                (1, '+7 999 111-22-33', 'mobile'),
                (1, '+7 999 222-33-44', 'home'),
                (2, '+7 999 333-44-55', 'mobile'),
                (2, '+7 999 444-55-66', 'work');
            
            -- Вставка курсов
            INSERT INTO student_courses (student_id, course, grade) VALUES
                (1, 'Математика', 'A'),
                (1, 'Физика', 'B+'),
                (1, 'История', 'A-'),
                (2, 'Физика', 'A'),
                (2, 'Химия', 'B');
            
            -- ============================================
            -- ПРОВЕРКА 1НФ
            -- ============================================
            
            -- Проверка: все ячейки атомарные
            SELECT '=== Студенты ===' AS info;
            SELECT * FROM students;
            
            SELECT '=== Телефоны (атомарные) ===' AS info;
            SELECT * FROM student_phones;
            
            SELECT '=== Курсы (атомарные) ===' AS info;
            SELECT * FROM student_courses;
            
            -- ============================================
            -- ПРИМЕРЫ ЗАПРОСОВ (преимущества 1НФ)
            -- ============================================
            
            -- 1. Поиск студентов по телефону
            SELECT 
                s.id,
                s.name,
                sp.phone
            FROM students s
            JOIN student_phones sp ON s.id = sp.student_id
            WHERE sp.phone LIKE '%999%';
            
            -- 2. Поиск студентов по курсу
            SELECT 
                s.id,
                s.name,
                sc.course,
                sc.grade
            FROM students s
            JOIN student_courses sc ON s.id = sc.student_id
            WHERE sc.course = 'Физика';
            
            -- 3. Полная информация о студенте
            SELECT 
                s.id,
                s.name,
                GROUP_CONCAT(DISTINCT sp.phone) AS phones,
                GROUP_CONCAT(DISTINCT sc.course) AS courses
            FROM students s
            LEFT JOIN student_phones sp ON s.id = sp.student_id
            LEFT JOIN student_courses sc ON s.id = sc.student_id
            GROUP BY s.id, s.name;
            
            -- 4. Студенты с количеством телефонов и курсов
            SELECT 
                s.id,
                s.name,
                COUNT(DISTINCT sp.id) AS phone_count,
                COUNT(DISTINCT sc.id) AS course_count
            FROM students s
            LEFT JOIN student_phones sp ON s.id = sp.student_id
            LEFT JOIN student_courses sc ON s.id = sc.student_id
            GROUP BY s.id, s.name
            ORDER BY phone_count DESC, course_count DESC;
            """;
        System.out.println(sql);
    }
}