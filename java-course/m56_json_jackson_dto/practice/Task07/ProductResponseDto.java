package m56_json_jackson_dto.practice.task07;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;

// ─── ProductResponseDto (исходящий DTO → JSON-ответ API) ─────────────────────
// TODO: добавьте @JsonInclude(JsonInclude.Include.NON_NULL)
class ProductResponseDto {

    private Long id;

    private String name;

    private double price;

    @JsonProperty("category_id")               // snake_case в JSON
    private Long categoryId;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    // deletedAt — намеренно ОТСУТСТВУЕТ в DTO (внутреннее поле Entity)

    public Long getId()                 { return id; }
    public String getName()             { return name; }
    public double getPrice()            { return price; }
    public Long getCategoryId()         { return categoryId; }
    public String getDescription()      { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id)                     { this.id = id; }
    public void setName(String name)               { this.name = name; }
    public void setPrice(double price)             { this.price = price; }
    public void setCategoryId(Long categoryId)     { this.categoryId = categoryId; }
    public void setDescription(String description) { this.description = description; }
    public void setCreatedAt(LocalDateTime dt)     { this.createdAt = dt; }
}
