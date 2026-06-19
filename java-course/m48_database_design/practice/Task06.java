package m48_database_design.practice;

/**
 * Задача 06 — Модуль 48: Связь «многие ко многим»
 *
 * ЗАДАНИЕ (проектирование):
 *   Спроектируйте связь M:N между студентами и курсами:
 *     - студент может записаться на много курсов;
 *     - на курс записано много студентов.
 *   1) объясните, почему нельзя реализовать M:N одним внешним ключом;
 *   2) создайте связующую (junction) таблицу enrollments
 *      со ссылками на обе таблицы и составным первичным ключом;
 *   3) добавьте в enrollments дополнительный атрибут связи
 *      (например, дату записи или оценку);
 *   4) напишите CREATE TABLE для students, courses и enrollments,
 *      затем пример INSERT (запись студента на курс).
 *
 * ПОДСКАЗКА:
 *   CREATE TABLE enrollments (
 *       student_id INT,
 *       course_id INT,
 *       enrolled_at DATE,
 *       PRIMARY KEY (student_id, course_id),
 *       FOREIGN KEY (student_id) REFERENCES students(id),
 *       FOREIGN KEY (course_id) REFERENCES courses(id)
 *   );
 */
public class Task06 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- СВЯЗЬ МНОГИЕ-КО-МНОГИМ (M:N)
            -- ============================================
            
            /*
            ================================================
            ЧАСТЬ 1: АНАЛИЗ СВЯЗИ M:N
            ================================================
            
            СУЩНОСТИ:
            1. Студенты (Students)
            2. Курсы (Courses)
            
            СВЯЗЬ: M:N (многие-ко-многим)
            - Один студент может записаться на МНОГО курсов
            - На один курс может записаться МНОГО студентов
            
            ПОЧЕМУ НЕЛЬЗЯ РЕАЛИЗОВАТЬ M:N ОДНИМ ВНЕШНИМ КЛЮЧОМ:
            
            1. Если добавить FK в students:
               - Один студент сможет быть только на одном курсе (1:1 или 1:N)
               - Нарушается правило M:N
            
            2. Если добавить FK в courses:
               - Один курс сможет иметь только одного студента (1:1 или 1:N)
               - Нарушается правило M:N
            
            3. Если добавить FK в обе таблицы:
               - Получится циклическая зависимость
               - Невозможно будет вставить данные (нужны оба ключа)
               - Нарушается ссылочная целостность
            
            РЕШЕНИЕ:
            Создать СВЯЗУЮЩУЮ (JUNCTION) таблицу, которая хранит пары
            (student_id, course_id) — это устраняет M:N.
            
            КАК ЭТО РАБОТАЕТ:
            - Один студент → много записей в enrollments
            - Один курс → много записей в enrollments
            - Каждая запись в enrollments — это конкретная связь
            */
            
            /*
            ================================================
            ЧАСТЬ 2: ПРОЕКТИРОВАНИЕ ТАБЛИЦ
            ================================================
            
            СТУДЕНТЫ (students):
            - id (PK)
            - name
            - email
            - phone
            
            КУРСЫ (courses):
            - id (PK)
            - name
            - description
            - credits
            
            ЗАПИСИ НА КУРСЫ (enrollments) — СВЯЗУЮЩАЯ ТАБЛИЦА:
            - student_id (FK → students.id)
            - course_id (FK → courses.id)
            - enrolled_at (дата записи) — ДОПОЛНИТЕЛЬНЫЙ АТРИБУТ
            - grade (оценка) — ДОПОЛНИТЕЛЬНЫЙ АТРИБУТ
            - status (статус) — ДОПОЛНИТЕЛЬНЫЙ АТРИБУТ
            
            СОСТАВНОЙ ПЕРВИЧНЫЙ КЛЮЧ: (student_id, course_id)
            Это гарантирует, что студент не может записаться на один курс дважды.
            */
            
            -- ============================================
            -- ЧАСТЬ 3: CREATE TABLE
            -- ============================================
            
            -- 1. Таблица студентов
            CREATE TABLE IF NOT EXISTS students (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                name       VARCHAR(100) NOT NULL,
                email      VARCHAR(100) UNIQUE,
                phone      VARCHAR(20),
                birth_date DATE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                INDEX idx_name (name),
                INDEX idx_email (email)
            );
            
            -- 2. Таблица курсов
            CREATE TABLE IF NOT EXISTS courses (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                name        VARCHAR(100) NOT NULL,
                description TEXT,
                credits     INT DEFAULT 3 CHECK (credits > 0),
                instructor  VARCHAR(100),
                max_students INT DEFAULT 30 CHECK (max_students > 0),
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                INDEX idx_name (name),
                INDEX idx_instructor (instructor)
            );
            
            -- 3. Связующая таблица (JUNCTION TABLE)
            CREATE TABLE IF NOT EXISTS enrollments (
                student_id  INT NOT NULL,
                course_id   INT NOT NULL,
                enrolled_at DATE NOT NULL DEFAULT CURRENT_DATE,
                grade       VARCHAR(5) DEFAULT NULL,
                status      VARCHAR(20) DEFAULT 'ACTIVE' 
                            CHECK (status IN ('ACTIVE', 'COMPLETED', 'WITHDRAWN', 'FAILED')),
                completed_at DATE,
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                -- Составной первичный ключ
                PRIMARY KEY (student_id, course_id),
                
                -- Внешние ключи
                FOREIGN KEY (student_id) REFERENCES students(id)
                    ON DELETE CASCADE 
                    ON UPDATE CASCADE,
                FOREIGN KEY (course_id) REFERENCES courses(id)
                    ON DELETE CASCADE 
                    ON UPDATE CASCADE,
                
                -- Индексы
                INDEX idx_student (student_id),
                INDEX idx_course (course_id),
                INDEX idx_enrolled (enrolled_at),
                INDEX idx_status (status),
                INDEX idx_grade (grade)
            );
            
            -- ============================================
            -- ЧАСТЬ 4: ПРИМЕРЫ INSERT
            -- ============================================
            
            -- Вставка студентов
            INSERT INTO students (id, name, email, phone) VALUES
                (1, 'Иван Петров', 'ivan@mail.com', '+7 999 111-22-33'),
                (2, 'Мария Смирнова', 'maria@mail.com', '+7 999 222-33-44'),
                (3, 'Петр Сидоров', 'petr@mail.com', '+7 999 333-44-55'),
                (4, 'Анна Кузнецова', 'anna@mail.com', '+7 999 444-55-66');
            
            -- Вставка курсов
            INSERT INTO courses (id, name, description, credits, instructor, max_students) VALUES
                (101, 'Математика', 'Высшая математика', 4, 'Проф. Смирнов', 30),
                (102, 'Физика', 'Общая физика', 4, 'Проф. Иванов', 25),
                (103, 'Программирование', 'Java для начинающих', 3, 'Доц. Петрова', 20),
                (104, 'Английский язык', 'Английский для IT', 2, 'Преп. Сидорова', 35);
            
            -- Вставка записей на курсы (M:N связь)
            INSERT INTO enrollments (student_id, course_id, enrolled_at, grade, status) VALUES
                -- Иван записался на Математику и Программирование
                (1, 101, '2024-01-15', 'A', 'ACTIVE'),
                (1, 103, '2024-01-16', NULL, 'ACTIVE'),
                
                -- Мария записалась на Физику и Английский
                (2, 102, '2024-01-17', NULL, 'ACTIVE'),
                (2, 104, '2024-01-18', NULL, 'ACTIVE'),
                
                -- Петр записался на Математику, Физику и Программирование
                (3, 101, '2024-01-19', 'B+', 'ACTIVE'),
                (3, 102, '2024-01-20', NULL, 'ACTIVE'),
                (3, 103, '2024-01-21', 'A-', 'COMPLETED'),
                
                -- Анна записалась на все курсы
                (4, 101, '2024-01-22', NULL, 'ACTIVE'),
                (4, 102, '2024-01-23', NULL, 'ACTIVE'),
                (4, 103, '2024-01-24', NULL, 'ACTIVE'),
                (4, 104, '2024-01-25', NULL, 'ACTIVE');
            
            -- ============================================
            -- ЧАСТЬ 5: ПРОВЕРКА И ЗАПРОСЫ
            -- ============================================
            
            -- 1. Проверка структуры
            SELECT '=== Студенты ===' AS info;
            SELECT * FROM students ORDER BY id;
            
            SELECT '=== Курсы ===' AS info;
            SELECT * FROM courses ORDER BY id;
            
            SELECT '=== Записи на курсы ===' AS info;
            SELECT * FROM enrollments ORDER BY student_id, course_id;
            
            -- 2. Получение всех курсов студента (Иван)
            SELECT '=== Курсы Ивана ===' AS info;
            SELECT 
                s.name AS student,
                c.name AS course,
                c.credits,
                e.enrolled_at,
                e.grade,
                e.status
            FROM students s
            JOIN enrollments e ON s.id = e.student_id
            JOIN courses c ON e.course_id = c.id
            WHERE s.name = 'Иван Петров'
            ORDER BY e.enrolled_at;
            
            -- 3. Получение всех студентов на курсе (Математика)
            SELECT '=== Студенты на Математике ===' AS info;
            SELECT 
                c.name AS course,
                s.name AS student,
                e.enrolled_at,
                e.grade,
                e.status
            FROM courses c
            JOIN enrollments e ON c.id = e.course_id
            JOIN students s ON e.student_id = s.id
            WHERE c.name = 'Математика'
            ORDER BY s.name;
            
            -- 4. Статистика по курсам
            SELECT '=== Статистика по курсам ===' AS info;
            SELECT 
                c.id,
                c.name AS course,
                c.instructor,
                COUNT(e.student_id) AS enrolled_students,
                ROUND(AVG(CASE WHEN e.grade IS NOT NULL THEN 
                    CASE e.grade
                        WHEN 'A' THEN 5
                        WHEN 'A-' THEN 4.5
                        WHEN 'B+' THEN 4
                        WHEN 'B' THEN 3.5
                        WHEN 'C' THEN 3
                        ELSE 0
                    END END), 2) AS avg_grade
            FROM courses c
            LEFT JOIN enrollments e ON c.id = e.course_id
            GROUP BY c.id, c.name, c.instructor
            ORDER BY enrolled_students DESC;
            
            -- 5. Студенты с количеством курсов
            SELECT '=== Студенты с количеством курсов ===' AS info;
            SELECT 
                s.id,
                s.name AS student,
                COUNT(e.course_id) AS course_count,
                GROUP_CONCAT(c.name ORDER BY c.name) AS courses
            FROM students s
            LEFT JOIN enrollments e ON s.id = e.student_id
            LEFT JOIN courses c ON e.course_id = c.id
            GROUP BY s.id, s.name
            ORDER BY course_count DESC;
            
            -- 6. Активные и завершенные курсы
            SELECT '=== Статусы записей ===' AS info;
            SELECT 
                status,
                COUNT(*) AS count,
                GROUP_CONCAT(CONCAT(s.name, ' - ', c.name) SEPARATOR '; ') AS details
            FROM enrollments e
            JOIN students s ON e.student_id = s.id
            JOIN courses c ON e.course_id = c.id
            GROUP BY status
            ORDER BY status;
            """;
        System.out.println(sql);
    }
}
