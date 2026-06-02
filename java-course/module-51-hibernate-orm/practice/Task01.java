/**
 * Задача 01 — Модуль 51: Разметить сущность Post аннотациями JPA
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Перед вами каркас класса Post. Расставьте необходимые JPA-аннотации:
 *     1) Пометьте класс как сущность (@Entity) и укажите имя таблицы "posts" (@Table).
 *     2) Поле id — первичный ключ (@Id) с автоинкрементом (@GeneratedValue(strategy = IDENTITY)).
 *     3) Поле title — колонка "title", NOT NULL, длина 255 (@Column).
 *     4) Поле content — колонка "content" типа TEXT (@Column с columnDefinition).
 *     5) Поле createdAt — колонка "created_at" (@Column).
 *     6) Убедитесь, что есть конструктор без аргументов (обязателен для JPA).
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   Класс Post корректно описывает таблицу "posts".
 *   Hibernate сможет создать схему: CREATE TABLE posts (id BIGINT AUTO_INCREMENT, title …).
 *
 * ПОДСКАЗКА:
 *   @Entity
 *   @Table(name = "posts")
 *   public class Post { … }
 *
 *   @Id
 *   @GeneratedValue(strategy = GenerationType.IDENTITY)
 *   private Long id;
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

// TODO: добавьте @Entity и @Table(name = "posts") к классу
public class Task01 {

    public static void main(String[] args) {
        // Точка входа — просто убедитесь, что класс Post компилируется с нужными аннотациями.
        // Создайте объект Post и выведите его поля.
        // TODO
    }
}

// ============================================================
// Сущность Post — расставьте аннотации (TODO-метки)
// ============================================================

// TODO: @Entity
// TODO: @Table(name = "posts")
class Post {

    // TODO: @Id
    // TODO: @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @Column(name = "title", nullable = false, length = 255)
    private String title;

    // TODO: @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // TODO: @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Конструктор без аргументов — ОБЯЗАТЕЛЕН для JPA (не удалять)
    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // TODO: добавьте геттеры и сеттеры для всех полей
}
