import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;

// ─── ProductCreateDto (входящий DTO ← тело POST-запроса) ─────────────────────
class ProductCreateDto {

    private String name;
    private double price;

    @JsonProperty("category_id")
    private Long categoryId;

    private String description;

    public String getName()        { return name; }
    public double getPrice()       { return price; }
    public Long getCategoryId()    { return categoryId; }
    public String getDescription() { return description; }

    public void setName(String name)               { this.name = name; }
    public void setPrice(double price)             { this.price = price; }
    public void setCategoryId(Long categoryId)     { this.categoryId = categoryId; }
    public void setDescription(String description) { this.description = description; }
}
