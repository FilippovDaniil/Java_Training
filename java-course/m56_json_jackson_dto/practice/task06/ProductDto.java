package m56_json_jackson_dto.practice.task06;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;

// Заготовка DTO — добавьте аннотации согласно заданию
// TODO: добавьте @JsonIgnoreProperties(ignoreUnknown = true)   — для шага 3
// TODO: добавьте @JsonInclude(JsonInclude.Include.NON_NULL)     — для шага 6
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String description;   // может быть null
    private String imageUrl;      // может быть null

    public Long getId()           { return id; }
    public String getName()       { return name; }
    public double getPrice()      { return price; }
    public String getDescription(){ return description; }
    public String getImageUrl()   { return imageUrl; }

    public void setId(Long id)             { this.id = id; }
    public void setName(String name)       { this.name = name; }
    public void setPrice(double price)     { this.price = price; }
    public void setDescription(String d)   { this.description = d; }
    public void setImageUrl(String url)    { this.imageUrl = url; }
}
