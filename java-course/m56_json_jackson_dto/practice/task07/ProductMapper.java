package m56_json_jackson_dto.practice.task07;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;

// ─── ProductMapper (маппинг Entity ↔ DTO) ────────────────────────────────────
class ProductMapper {

    // Entity → ResponseDto
    public static ProductResponseDto toDto(ProductEntity entity) {
        // TODO: реализуйте маппинг
        //   ProductResponseDto dto = new ProductResponseDto();
        //   dto.setId(entity.getId());
        //   dto.setName(...);
        //   dto.setPrice(...);
        //   dto.setCategoryId(...);
        //   dto.setDescription(...);
        //   dto.setCreatedAt(...);
        //   return dto;
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setCategoryId(entity.getCategoryId());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
        //return null; // заглушка — замените реализацией
    }

    // CreateDto → Entity (новый объект, без id и служебных полей)
    public static ProductEntity toEntity(ProductCreateDto dto) {
        // TODO: реализуйте маппинг
        //   ProductEntity entity = new ProductEntity();
        //   entity.setName(...);
        //   entity.setPrice(...);
        //   entity.setCategoryId(...);
        //   entity.setDescription(...);
        //   // id, createdAt, deletedAt — НЕ заполняем (управляет БД)
        //   return entity;
        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setCategoryId(dto.getCategoryId());
        entity.setDescription(dto.getDescription());
        return entity; // заглушка — замените реализацией
    }
}
