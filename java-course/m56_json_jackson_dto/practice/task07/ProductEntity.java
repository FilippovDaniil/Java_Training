package m56_json_jackson_dto.practice.task07;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.ToString;

import java.time.LocalDateTime;

// ─── Entity (слой БД / JPA) ───────────────────────────────────────────────────
// Содержит все поля, включая служебные (deletedAt, version)
// НИКОГДА не сериализуется в JSON напрямую
@ToString
class ProductEntity {
    private Long id;
    private String name;
    private double price;
    private Long categoryId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;   // мягкое удаление — клиенту не нужно!

    public Long getId()                 { return id; }
    public String getName()             { return name; }
    public double getPrice()            { return price; }
    public Long getCategoryId()         { return categoryId; }
    public String getDescription()      { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getDeletedAt() { return deletedAt; }

    public void setId(Long id)                     { this.id = id; }
    public void setName(String name)               { this.name = name; }
    public void setPrice(double price)             { this.price = price; }
    public void setCategoryId(Long categoryId)     { this.categoryId = categoryId; }
    public void setDescription(String description) { this.description = description; }
    public void setCreatedAt(LocalDateTime dt)     { this.createdAt = dt; }
    public void setDeletedAt(LocalDateTime dt)     { this.deletedAt = dt; }
}
