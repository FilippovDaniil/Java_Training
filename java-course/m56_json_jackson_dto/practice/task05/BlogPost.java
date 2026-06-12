package m56_json_jackson_dto.practice.task05;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Готовая модель
class BlogPost {
    private Long id;
    private String title;

    // Дата без времени: "2024-03-15"
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedOn;

    // Дата со временем: "2024-03-15T10:30:00"
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    // Конструктор, геттеры, сеттеры
    public BlogPost() {}
    public BlogPost(Long id, String title, LocalDate publishedOn, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.publishedOn = publishedOn;
        this.createdAt = createdAt;
    }

    public Long getId()                 { return id; }
    public String getTitle()            { return title; }
    public LocalDate getPublishedOn()   { return publishedOn; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id)                     { this.id = id; }
    public void setTitle(String title)             { this.title = title; }
    public void setPublishedOn(LocalDate d)        { this.publishedOn = d; }
    public void setCreatedAt(LocalDateTime dt)     { this.createdAt = dt; }
}
