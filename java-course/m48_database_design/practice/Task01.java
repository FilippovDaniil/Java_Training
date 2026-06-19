package m48_database_design.practice;

/**
 * Задача 01 — Модуль 48: Выделение сущностей и атрибутов
 *
 * ЗАДАНИЕ (проектирование):
 *   Дано текстовое описание предметной области «Библиотека»:
 *     "В библиотеке есть книги (название, автор, год, ISBN) и читатели
 *      (имя, билет, телефон). Читатель может брать книги, у выдачи есть
 *      дата выдачи и дата возврата."
 *   1) Выделите сущности и их атрибуты;
 *   2) опишите их в комментарии в виде списка;
 *   3) набросайте CREATE TABLE для каждой сущности (без связей пока).
 *
 * ПОДСКАЗКА:
 *   Сущности обычно — существительные предметной области (книга,
 *   читатель, выдача). Атрибуты — их свойства.
 */
public class Task01 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ПРОЕКТИРОВАНИЕ БАЗЫ ДАННЫХ "БИБЛИОТЕКА"
            -- ============================================
            
            /*
            ================================================
            ЧАСТЬ 1: ВЫДЕЛЕНИЕ СУЩНОСТЕЙ И АТРИБУТОВ
            ================================================
            
            АНАЛИЗ ТЕКСТА:
            "В библиотеке есть книги (название, автор, год, ISBN) и читатели
            (имя, билет, телефон). Читатель может брать книги, у выдачи есть
            дата выдачи и дата возврата."
            
            КЛЮЧЕВЫЕ СУЩЕСТВИТЕЛЬНЫЕ (СУЩНОСТИ):
            1. КНИГА (Book)
            2. ЧИТАТЕЛЬ (Reader / User)
            3. ВЫДАЧА (Loan / Borrowing) - связь между читателем и книгой
            
            ================================================
            СУЩНОСТЬ 1: КНИГА (Book)
            ================================================
            Атрибуты:
            - id: уникальный идентификатор (PRIMARY KEY)
            - title: название книги (NOT NULL)
            - author: автор книги
            - year: год издания
            - isbn: международный стандартный книжный номер (уникальный)
            - genre: жанр (дополнительно)
            - pages: количество страниц (дополнительно)
            - publisher: издательство (дополнительно)
            - copies: количество экземпляров (дополнительно)
            
            ================================================
            СУЩНОСТЬ 2: ЧИТАТЕЛЬ (Reader)
            ================================================
            Атрибуты:
            - id: уникальный идентификатор (PRIMARY KEY)
            - name: имя читателя (NOT NULL)
            - card_number: номер библиотечного билета (уникальный)
            - phone: телефон
            - email: электронная почта
            - address: адрес
            - registration_date: дата регистрации
            
            ================================================
            СУЩНОСТЬ 3: ВЫДАЧА (Loan)
            ================================================
            Атрибуты:
            - id: уникальный идентификатор (PRIMARY KEY)
            - book_id: ссылка на книгу (FOREIGN KEY)
            - reader_id: ссылка на читателя (FOREIGN KEY)
            - loan_date: дата выдачи (NOT NULL)
            - due_date: планируемая дата возврата (NOT NULL)
            - return_date: фактическая дата возврата
            - status: статус выдачи (выдана/возвращена/просрочена)
            - fine: штраф (за просрочку)
            */
            
            -- ============================================
            -- ЧАСТЬ 2: CREATE TABLE (без связей)
            -- ============================================
            
            -- 1. Таблица "Книги"
            CREATE TABLE IF NOT EXISTS books (
                id         INT PRIMARY KEY AUTO_INCREMENT,
                title      VARCHAR(200) NOT NULL,
                author     VARCHAR(100),
                year       INT,
                isbn       VARCHAR(20) UNIQUE,
                genre      VARCHAR(50),
                pages      INT,
                publisher  VARCHAR(100),
                copies     INT DEFAULT 1,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            );
            
            -- 2. Таблица "Читатели"
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
            
            -- 3. Таблица "Выдача книг"
            CREATE TABLE IF NOT EXISTS loans (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                book_id     INT NOT NULL,
                reader_id   INT NOT NULL,
                loan_date   DATE NOT NULL,
                due_date    DATE NOT NULL,
                return_date DATE,
                status      VARCHAR(20) DEFAULT 'ACTIVE' 
                            CHECK (status IN ('ACTIVE', 'RETURNED', 'OVERDUE')),
                fine        DECIMAL(10, 2) DEFAULT 0,
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            );
            """;
        System.out.println(sql);
    }
}
