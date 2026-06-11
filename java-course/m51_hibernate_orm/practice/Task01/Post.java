package m51_hibernate_orm.practice.task01;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

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
