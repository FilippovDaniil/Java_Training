package m48_database_design.practice;

/**
 * Задача 07 — Модуль 48 (МИНИ-ПРОЕКТ): Проектирование БД «с нуля»
 *
 * ЗАДАНИЕ (полный цикл проектирования):
 *   Спроектируйте БД для онлайн-кинотеатра по требованиям:
 *     "Есть фильмы (название, год, жанр, длительность). Пользователи
 *      (имя, email) оформляют подписки (тариф, дата начала/конца).
 *      Пользователи оставляют отзывы к фильмам (оценка 1-10, текст,
 *      дата). Один фильм может иметь несколько жанров."
 *
 *   Пройдите все шаги:
 *     1) выделите сущности и атрибуты (комментарий);
 *     2) определите связи и их кардинальность
 *        (учтите M:N между фильмами и жанрами!);
 *     3) назначьте ключи (PK, FK, при необходимости составные);
 *     4) проверьте нормализацию: схема должна быть в 3НФ
 *        (никаких повторяющихся групп, частичных и транзитивных
 *        зависимостей);
 *     5) напишите итоговый SQL: все CREATE TABLE с ключами и
 *        ограничениями + пример наполнения данными;
 *     6) напишите 2-3 проверочных SELECT (например, средняя оценка
 *        фильма, активные подписки).
 *
 * ЦЕЛЬ:
 *   Пройти путь от текстовых требований до нормализованной (3НФ)
 *   рабочей схемы — ключевой навык проектирования БД.
 *
 * ПОДСКАЗКА:
 *   Жанры фильма — это M:N (фильм↔жанр) → связующая таблица.
 *   Отзывы — 1:N от пользователя и 1:N от фильма.
 *   Сверяйтесь с правилами 1НФ→2НФ→3НФ из theory.md.
 */
public class Task07 {
    public static void main(String[] args) {
        String sql = """
            -- ============================================
            -- ОНЛАЙН-КИНОТЕАТР: ПРОЕКТИРОВАНИЕ БД
            -- ============================================
            
            /*
            ================================================
            ЧАСТЬ 1: ВЫДЕЛЕНИЕ СУЩНОСТЕЙ И АТРИБУТОВ
            ================================================
            
            АНАЛИЗ ТРЕБОВАНИЙ:
            "Есть фильмы (название, год, жанр, длительность). 
             Пользователи (имя, email) оформляют подписки (тариф, дата начала/конца). 
             Пользователи оставляют отзывы к фильмам (оценка 1-10, текст, дата). 
             Один фильм может иметь несколько жанров."
            
            СУЩНОСТИ И АТРИБУТЫ:
            
            1. FILMS (Фильмы)
               - id (PK) - уникальный идентификатор
               - title - название фильма (NOT NULL)
               - year - год выпуска
               - duration - длительность в минутах
               - description - описание фильма
               - rating - рейтинг (средний)
               - poster_url - ссылка на постер
               - created_at - дата создания записи
               - updated_at - дата обновления
            
            2. GENRES (Жанры)
               - id (PK) - уникальный идентификатор
               - name - название жанра (NOT NULL, UNIQUE)
               - description - описание жанра
            
            3. FILM_GENRES (Связь фильмов и жанров) - M:N
               - film_id (FK) - ссылка на фильм
               - genre_id (FK) - ссылка на жанр
               - PRIMARY KEY (film_id, genre_id)
            
            4. USERS (Пользователи)
               - id (PK) - уникальный идентификатор
               - name - имя пользователя (NOT NULL)
               - email - email (UNIQUE, NOT NULL)
               - password_hash - хеш пароля
               - registered_at - дата регистрации
               - is_active - активен ли пользователь
               - created_at - дата создания записи
               - updated_at - дата обновления
            
            5. SUBSCRIPTIONS (Подписки)
               - id (PK) - уникальный идентификатор
               - user_id (FK) - ссылка на пользователя
               - plan - тарифный план (BASIC, PREMIUM, FAMILY)
               - start_date - дата начала подписки (NOT NULL)
               - end_date - дата окончания подписки (NOT NULL)
               - status - статус (ACTIVE, EXPIRED, CANCELLED)
               - auto_renew - автоматическое продление
               - price - цена подписки
               - created_at - дата создания записи
               - updated_at - дата обновления
            
            6. REVIEWS (Отзывы)
               - id (PK) - уникальный идентификатор
               - user_id (FK) - ссылка на пользователя
               - film_id (FK) - ссылка на фильм
               - rating - оценка (1-10, NOT NULL)
               - text - текст отзыва
               - review_date - дата отзыва (DEFAULT CURRENT_DATE)
               - is_verified - подтвержденный отзыв
               - created_at - дата создания записи
               - updated_at - дата обновления
            */
            
            /*
            ================================================
            ЧАСТЬ 2: СВЯЗИ И КАРДИНАЛЬНОСТЬ
            ================================================
            
            ┌─────────────────────────────────────────────────────────────┐
            │ СВЯЗЬ                    │ ТИП    │ РЕАЛИЗАЦИЯ            │
            ├──────────────────────────┼────────┼───────────────────────┤
            │ Films ↔ Genres           │ M:N    │ film_genres           │
            │ (фильм-жанр)             │        │ (junction table)      │
            ├──────────────────────────┼────────┼───────────────────────┤
            │ Users ↔ Subscriptions    │ 1:N    │ subscriptions.user_id │
            │ (пользователь-подписка)  │        │                       │
            ├──────────────────────────┼────────┼───────────────────────┤
            │ Users ↔ Reviews          │ 1:N    │ reviews.user_id       │
            │ (пользователь-отзыв)     │        │                       │
            ├──────────────────────────┼────────┼───────────────────────┤
            │ Films ↔ Reviews          │ 1:N    │ reviews.film_id       │
            │ (фильм-отзыв)            │        │                       │
            └──────────────────────────┴────────┴───────────────────────┘
            
            ER-ДИАГРАММА:
            
            ┌─────────────┐     ┌─────────────┐     ┌─────────────┐
            │   GENRES    │     │ FILM_GENRES │     │   FILMS     │
            ├─────────────┤     ├─────────────┤     ├─────────────┤
            │ id (PK)     │◄────│ genre_id    │     │ id (PK)     │
            │ name        │     │ film_id     │────►│ title       │
            │ description │     └─────────────┘     │ year        │
            └─────────────┘                        │ duration    │
                                                   │ description │
                                                   └─────────────┘
                                                         │
                                                         │ 1:N
                                                         ▼
            ┌─────────────┐     ┌─────────────┐     ┌─────────────┐
            │   USERS     │     │  REVIEWS    │     │             │
            ├─────────────┤     ├─────────────┤     │             │
            │ id (PK)     │◄────│ user_id     │     │             │
            │ name        │     │ film_id     │────►│             │
            │ email       │     │ rating      │     │             │
            │ password    │     │ text        │     │             │
            │ registered  │     │ review_date │     │             │
            └─────────────┘     └─────────────┘     └─────────────┘
                  │
                  │ 1:N
                  ▼
            ┌─────────────┐
            │SUBSCRIPTIONS│
            ├─────────────┤
            │ id (PK)     │
            │ user_id     │
            │ plan        │
            │ start_date  │
            │ end_date    │
            │ status      │
            └─────────────┘
            */
            
            /*
            ================================================
            ЧАСТЬ 3: ПРОВЕРКА НОРМАЛИЗАЦИИ (3НФ)
            ================================================
            
            1НФ (Атомарность):
            ✅ Все ячейки содержат атомарные значения
            ✅ Нет повторяющихся групп
            ✅ Каждая запись уникальна
            
            2НФ (Нет частичных зависимостей):
            ✅ Все таблицы имеют первичные ключи
            ✅ Нет частичных зависимостей (все таблицы имеют один PK,
               кроме film_genres с составным ключом, где оба поля часть PK)
            ✅ Атрибуты жанров вынесены в отдельную таблицу
            
            3НФ (Нет транзитивных зависимостей):
            ✅ Нет зависимостей от неключевых атрибутов
            ✅ Все атрибуты зависят только от PK
            ✅ Информация о подписках не дублируется в users
            ✅ Информация о фильмах не дублируется в reviews
            */
            
            -- ============================================
            -- ЧАСТЬ 4: CREATE TABLE
            -- ============================================
            
            -- 1. Таблица фильмов
            CREATE TABLE IF NOT EXISTS films (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                title       VARCHAR(200) NOT NULL,
                year        INT CHECK (year >= 1888 AND year <= YEAR(CURRENT_DATE)),
                duration    INT CHECK (duration > 0),
                description TEXT,
                rating      DECIMAL(3, 2) DEFAULT 0 CHECK (rating >= 0 AND rating <= 10),
                poster_url  VARCHAR(500),
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                INDEX idx_title (title),
                INDEX idx_year (year)
            );
            
            -- 2. Таблица жанров
            CREATE TABLE IF NOT EXISTS genres (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                name        VARCHAR(50) NOT NULL UNIQUE,
                description TEXT,
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                INDEX idx_name (name)
            );
            
            -- 3. Связующая таблица фильмов и жанров (M:N)
            CREATE TABLE IF NOT EXISTS film_genres (
                film_id     INT NOT NULL,
                genre_id    INT NOT NULL,
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                
                PRIMARY KEY (film_id, genre_id),
                FOREIGN KEY (film_id) REFERENCES films(id) 
                    ON DELETE CASCADE ON UPDATE CASCADE,
                FOREIGN KEY (genre_id) REFERENCES genres(id) 
                    ON DELETE CASCADE ON UPDATE CASCADE
            );
            
            -- 4. Таблица пользователей
            CREATE TABLE IF NOT EXISTS users (
                id            INT PRIMARY KEY AUTO_INCREMENT,
                name          VARCHAR(100) NOT NULL,
                email         VARCHAR(100) NOT NULL UNIQUE,
                password_hash VARCHAR(255) NOT NULL,
                registered_at DATE DEFAULT CURRENT_DATE,
                is_active     BOOLEAN DEFAULT TRUE,
                created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                INDEX idx_name (name),
                INDEX idx_email (email)
            );
            
            -- 5. Таблица подписок
            CREATE TABLE IF NOT EXISTS subscriptions (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                user_id     INT NOT NULL,
                plan        VARCHAR(20) NOT NULL 
                            CHECK (plan IN ('BASIC', 'PREMIUM', 'FAMILY')),
                start_date  DATE NOT NULL,
                end_date    DATE NOT NULL CHECK (end_date > start_date),
                status      VARCHAR(20) DEFAULT 'ACTIVE'
                            CHECK (status IN ('ACTIVE', 'EXPIRED', 'CANCELLED')),
                auto_renew  BOOLEAN DEFAULT TRUE,
                price       DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES users(id) 
                    ON DELETE CASCADE ON UPDATE CASCADE,
                
                INDEX idx_user (user_id),
                INDEX idx_status (status),
                INDEX idx_dates (start_date, end_date)
            );
            
            -- 6. Таблица отзывов
            CREATE TABLE IF NOT EXISTS reviews (
                id          INT PRIMARY KEY AUTO_INCREMENT,
                user_id     INT NOT NULL,
                film_id     INT NOT NULL,
                rating      INT NOT NULL CHECK (rating >= 1 AND rating <= 10),
                text        TEXT,
                review_date DATE DEFAULT CURRENT_DATE,
                is_verified BOOLEAN DEFAULT FALSE,
                created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                
                FOREIGN KEY (user_id) REFERENCES users(id) 
                    ON DELETE CASCADE ON UPDATE CASCADE,
                FOREIGN KEY (film_id) REFERENCES films(id) 
                    ON DELETE CASCADE ON UPDATE CASCADE,
                
                INDEX idx_user (user_id),
                INDEX idx_film (film_id),
                INDEX idx_rating (rating),
                INDEX idx_date (review_date),
                
                -- Уникальность: один пользователь может оставить только один отзыв на фильм
                UNIQUE KEY unique_review (user_id, film_id)
            );
            
            -- ============================================
            -- ЧАСТЬ 5: НАПОЛНЕНИЕ ДАННЫМИ
            -- ============================================
            
            -- 1. Жанры
            INSERT INTO genres (name, description) VALUES
                ('Драма', 'Фильмы, основанные на серьезных эмоциональных переживаниях'),
                ('Комедия', 'Юмористические фильмы'),
                ('Фантастика', 'Научно-фантастические фильмы'),
                ('Боевик', 'Действие и спецэффекты'),
                ('Триллер', 'Напряженные и захватывающие фильмы'),
                ('Романтика', 'Любовные истории'),
                ('Ужасы', 'Фильмы ужасов');
            
            -- 2. Фильмы
            INSERT INTO films (id, title, year, duration, description, rating, poster_url) VALUES
                (1, 'Побег из Шоушенка', 1994, 142, 'Два заключенных находят общий язык за решеткой', 9.3, 'shawshank.jpg'),
                (2, 'Крестный отец', 1972, 175, 'История семьи Корлеоне', 9.2, 'godfather.jpg'),
                (3, 'Темный рыцарь', 2008, 152, 'Бэтмен против Джокера', 9.0, 'dark_knight.jpg'),
                (4, 'Форрест Гамп', 1994, 142, 'Необычная жизнь простого человека', 8.8, 'forrest_gump.jpg'),
                (5, 'Начало', 2010, 148, 'Внедрение в сны', 8.8, 'inception.jpg'),
                (6, 'Матрица', 1999, 136, 'Борьба с искусственным интеллектом', 8.7, 'matrix.jpg'),
                (7, 'Интерстеллар', 2014, 169, 'Путешествие сквозь космос', 8.6, 'interstellar.jpg');
            
            -- 3. Связь фильмов с жанрами (M:N)
            INSERT INTO film_genres (film_id, genre_id) VALUES
                (1, 1), (1, 5),   -- Побег из Шоушенка: Драма, Триллер
                (2, 1), (2, 5),   -- Крестный отец: Драма, Триллер
                (3, 2), (3, 4),   -- Темный рыцарь: Боевик, Драма
                (4, 1), (4, 6),   -- Форрест Гамп: Драма, Романтика
                (5, 3), (5, 5),   -- Начало: Фантастика, Триллер
                (6, 3), (6, 4),   -- Матрица: Фантастика, Боевик
                (7, 1), (7, 3);   -- Интерстеллар: Драма, Фантастика
            
            -- 4. Пользователи
            INSERT INTO users (id, name, email, password_hash, registered_at) VALUES
                (1, 'Иван Петров', 'ivan@mail.com', 'hash123', '2023-01-15'),
                (2, 'Мария Смирнова', 'maria@mail.com', 'hash456', '2023-02-20'),
                (3, 'Петр Сидоров', 'petr@mail.com', 'hash789', '2023-03-10'),
                (4, 'Анна Кузнецова', 'anna@mail.com', 'hash012', '2023-04-05');
            
            -- 5. Подписки
            INSERT INTO subscriptions (user_id, plan, start_date, end_date, status, auto_renew, price) VALUES
                (1, 'PREMIUM', '2024-01-01', '2024-12-31', 'ACTIVE', TRUE, 999.00),
                (2, 'BASIC', '2024-02-01', '2024-07-31', 'ACTIVE', TRUE, 499.00),
                (3, 'FAMILY', '2024-03-01', '2024-08-31', 'ACTIVE', FALSE, 1499.00),
                (4, 'PREMIUM', '2023-06-01', '2023-11-30', 'EXPIRED', FALSE, 999.00);
            
            -- 6. Отзывы
            INSERT INTO reviews (user_id, film_id, rating, text, review_date, is_verified) VALUES
                (1, 1, 10, 'Лучший фильм всех времен!', '2024-01-20', TRUE),
                (1, 2, 9, 'Шедевр киноискусства', '2024-01-25', TRUE),
                (2, 3, 9, 'Хит Леджера - лучший Джокер', '2024-02-15', TRUE),
                (2, 4, 8, 'Трогательная история', '2024-02-20', FALSE),
                (3, 5, 9, 'Гениальный сюжет!', '2024-03-10', TRUE),
                (3, 6, 8, 'Классика научной фантастики', '2024-03-15', FALSE),
                (4, 7, 9, 'Невероятная космическая эпопея', '2024-04-01', TRUE),
                (1, 3, 8, 'Хороший фильм, но не лучший у Нолана', '2024-04-05', TRUE);
            
            -- ============================================
            -- ЧАСТЬ 6: ПРОВЕРОЧНЫЕ SELECT-ЗАПРОСЫ
            -- ============================================
            
            -- 1. Средняя оценка каждого фильма
            SELECT '=== 1. СРЕДНЯЯ ОЦЕНКА ФИЛЬМОВ ===' AS info;
            SELECT 
                f.id,
                f.title,
                f.year,
                ROUND(AVG(r.rating), 2) AS avg_rating,
                COUNT(r.id) AS review_count,
                f.rating AS official_rating
            FROM films f
            LEFT JOIN reviews r ON f.id = r.film_id
            GROUP BY f.id, f.title, f.year, f.rating
            HAVING COUNT(r.id) > 0
            ORDER BY avg_rating DESC;
            
            -- 2. Активные подписки
            SELECT '=== 2. АКТИВНЫЕ ПОДПИСКИ ===' AS info;
            SELECT 
                u.name AS user,
                u.email,
                s.plan,
                s.start_date,
                s.end_date,
                DATEDIFF(s.end_date, CURRENT_DATE) AS days_remaining,
                s.price,
                s.auto_renew
            FROM subscriptions s
            JOIN users u ON s.user_id = u.id
            WHERE s.status = 'ACTIVE' AND s.end_date >= CURRENT_DATE
            ORDER BY days_remaining;
            
            -- 3. Фильмы с жанрами
            SELECT '=== 3. ФИЛЬМЫ С ЖАНРАМИ ===' AS info;
            SELECT 
                f.id,
                f.title,
                f.year,
                f.duration,
                GROUP_CONCAT(g.name ORDER BY g.name SEPARATOR ', ') AS genres,
                f.rating
            FROM films f
            LEFT JOIN film_genres fg ON f.id = fg.film_id
            LEFT JOIN genres g ON fg.genre_id = g.id
            GROUP BY f.id, f.title, f.year, f.duration, f.rating
            ORDER BY f.title;
            
            -- 4. Пользователи с количеством отзывов
            SELECT '=== 4. ПОЛЬЗОВАТЕЛИ С ОТЗЫВАМИ ===' AS info;
            SELECT 
                u.id,
                u.name,
                u.email,
                COUNT(r.id) AS review_count,
                ROUND(AVG(r.rating), 2) AS avg_rating,
                u.registered_at
            FROM users u
            LEFT JOIN reviews r ON u.id = r.user_id
            GROUP BY u.id, u.name, u.email, u.registered_at
            ORDER BY review_count DESC;
            
            -- 5. Популярные жанры (по количеству просмотров/отзывов)
            SELECT '=== 5. ПОПУЛЯРНЫЕ ЖАНРЫ ===' AS info;
            SELECT 
                g.name AS genre,
                COUNT(DISTINCT f.id) AS film_count,
                COUNT(r.id) AS review_count,
                ROUND(AVG(r.rating), 2) AS avg_rating
            FROM genres g
            LEFT JOIN film_genres fg ON g.id = fg.genre_id
            LEFT JOIN films f ON fg.film_id = f.id
            LEFT JOIN reviews r ON f.id = r.film_id
            GROUP BY g.id, g.name
            ORDER BY review_count DESC;
            
            -- 6. Просроченные подписки
            SELECT '=== 6. ПРОСРОЧЕННЫЕ ПОДПИСКИ ===' AS info;
            SELECT 
                u.name AS user,
                s.plan,
                s.end_date,
                DATEDIFF(CURRENT_DATE, s.end_date) AS days_expired
            FROM subscriptions s
            JOIN users u ON s.user_id = u.id
            WHERE s.status = 'EXPIRED' AND s.end_date < CURRENT_DATE
            ORDER BY days_expired DESC;
            
            -- 7. Полная информация о фильме (фильм + жанры + рейтинг)
            SELECT '=== 7. ПОЛНАЯ ИНФОРМАЦИЯ О ФИЛЬМЕ (Начало) ===' AS info;
            SELECT 
                f.id,
                f.title AS film_title,
                f.year,
                f.duration,
                f.description,
                GROUP_CONCAT(DISTINCT g.name ORDER BY g.name SEPARATOR ', ') AS genres,
                f.rating AS official_rating,
                COUNT(DISTINCT r.id) AS total_reviews,
                ROUND(AVG(r.rating), 2) AS average_rating,
                MIN(r.rating) AS min_rating,
                MAX(r.rating) AS max_rating
            FROM films f
            LEFT JOIN film_genres fg ON f.id = fg.film_id
            LEFT JOIN genres g ON fg.genre_id = g.id
            LEFT JOIN reviews r ON f.id = r.film_id
            WHERE f.title = 'Начало'
            GROUP BY f.id, f.title, f.year, f.duration, f.description, f.rating;
            """;
        System.out.println(sql);
    }
}
