package m48_database_design.practice;

/**
 * Задача 02 — Модуль 48: Ключи и связи
 *
 * ЗАДАНИЕ (проектирование):
 *   Для схемы «Библиотека» из Task01:
 *   1) назначьте первичные ключи (PK) каждой таблице;
 *   2) определите связь между читателем, книгой и выдачей
 *      (какая кардинальность: 1:N? M:N?);
 *   3) добавьте внешние ключи (FK), реализующие эти связи;
 *   4) напишите итоговые CREATE TABLE с PK и FK.
 *
 * ПОДСКАЗКА:
 *   Выдача (loans) связывает читателя и книгу — это место для FK на оба.
 *   Если одну книгу могут брать много раз, а читатель берёт много книг —
 *   связь реализуется через таблицу выдач.
 */
public class Task02 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- БИБЛИОТЕКА: КЛЮЧИ И СВЯЗИ
            -- ============================================
            
            /*
            ================================================
            АНАЛИЗ СВЯЗЕЙ
            ================================================
            
            СУЩНОСТИ:
            1. books (книги)
            2. readers (читатели)
            3. loans (выдачи)
            
            СВЯЗИ:
            - Книга и Выдача: 1 : N
              Одна книга может быть выдана много раз (в разное время)
            
            - Читатель и Выдача: 1 : N
              Один читатель может взять много книг
            
            - Книга и Читатель: M : N
              Много книг могут быть взяты многими читателями
              (реализуется через таблицу loans)
            
            ПЕРВИЧНЫЕ КЛЮЧИ (PK):
            - books:   id
            - readers: id
            - loans:   id
            
            ВНЕШНИЕ КЛЮЧИ (FK):
            - loans.book_id   -> books.id
            - loans.reader_id -> readers.id
            */
            
            -- ============================================
            -- ТАБЛИЦА books (Книги)
            -- ============================================
            CREATE TABLE IF NOT EXISTS books (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                title       VARCHAR(200) NOT NULL,
                author      VARCHAR(100),
                year        INT,
                isbn        VARCHAR(20) UNIQUE,
                genre       VARCHAR(50),
                pages       INT,
                publisher   VARCHAR(100),
                copies      INT DEFAULT 1,
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            );
            
            -- ============================================
            -- ТАБЛИЦА readers (Читатели)
            -- ============================================
            CREATE TABLE IF NOT EXISTS readers (
                id                INT PRIMARY KEY AUTO_INCREMENT,
                name              VARCHAR(100) NOT NULL,
                card_number       VARCHAR(20) UNIQUE NOT NULL,
                phone             VARCHAR(20),
                email             VARCHAR(100) UNIQUE,
                address           VARCHAR(200),
                registration_date DATE DEFAULT CURRENT_DATE,
                is_active         BOOLEAN DEFAULT TRUE,
                created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            );
            
            -- ============================================
            -- ТАБЛИЦА loans (Выдача книг) - СВЯЗУЮЩАЯ ТАБЛИЦА
            -- ============================================
            CREATE TABLE IF NOT EXISTS loans (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                book_id     INT NOT NULL,
                reader_id   INT NOT NULL,
                loan_date   DATE NOT NULL,
                due_date    DATE NOT NULL,
                return_date DATE,
                status      VARCHAR(20) DEFAULT 'ACTIVE' 
                            CHECK (status IN ('ACTIVE', 'RETURNED', 'OVERDUE', 'LOST')),
                fine        DECIMAL(10, 2) DEFAULT 0 CHECK (fine >= 0),
                notes       TEXT,
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                -- ВНЕШНИЕ КЛЮЧИ
                FOREIGN KEY (book_id) REFERENCES books(id) 
                    ON DELETE CASCADE 
                    ON UPDATE CASCADE,
                FOREIGN KEY (reader_id) REFERENCES readers(id) 
                    ON DELETE CASCADE 
                    ON UPDATE CASCADE
            );
            
            -- ============================================
            -- ДОПОЛНИТЕЛЬНЫЕ ИНДЕКСЫ
            -- ============================================
            CREATE INDEX idx_loans_book_id ON loans(book_id);
            CREATE INDEX idx_loans_reader_id ON loans(reader_id);
            CREATE INDEX idx_loans_dates ON loans(loan_date, due_date);
            CREATE INDEX idx_loans_status ON loans(status);
            CREATE INDEX idx_books_title ON books(title);
            CREATE INDEX idx_books_author ON books(author);
            CREATE INDEX idx_readers_name ON readers(name);
            CREATE INDEX idx_readers_card ON readers(card_number);
            
            -- ============================================
            -- ПРОВЕРКА СВЯЗЕЙ
            -- ============================================
            /*
            ПРОВЕРКА ВНЕШНИХ КЛЮЧЕЙ:
            SELECT 
                CONSTRAINT_NAME,
                TABLE_NAME,
                COLUMN_NAME,
                REFERENCED_TABLE_NAME,
                REFERENCED_COLUMN_NAME
            FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
            WHERE TABLE_NAME IN ('loans')
            AND REFERENCED_TABLE_NAME IS NOT NULL;
            
            КАРДИНАЛЬНОСТЬ:
            - Одна книга может иметь много выдач (1:N)
            - Один читатель может иметь много выдач (1:N)
            - Многие книги могут быть взяты многими читателями (M:N через loans)
            */
            """;
        System.out.println(sql);
    }
}
