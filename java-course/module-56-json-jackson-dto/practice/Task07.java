/**
 * Задача 07 (МИНИ-ПРОЕКТ) — Модуль 56: DTO-слой REST-сервиса магазина
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.fasterxml.jackson.core:jackson-databind:2.17.x
 *   com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.x (для LocalDateTime)
 *
 * ЗАДАНИЕ:
 *   В реальном REST-сервисе между Entity (JPA) и JSON (API) всегда стоит слой DTO.
 *   Ваша задача — реализовать маппинг Entity ↔ DTO и JSON-сериализацию.
 *
 *   ЧАСТЬ 1 — Маппинг Entity → ResponseDto (ответ API):
 *     Реализуйте ProductMapper.toDto(ProductEntity) — заполните все поля ProductResponseDto.
 *     Поле deletedAt из Entity НЕ должно попасть в DTO (внутреннее).
 *
 *   ЧАСТЬ 2 — Маппинг RequestDto → Entity (запрос от клиента):
 *     Реализуйте ProductMapper.toEntity(ProductCreateDto) — создайте новый ProductEntity.
 *     Поля id, createdAt, deletedAt при создании НЕ заполняются (они управляются БД).
 *
 *   ЧАСТЬ 3 — Сериализация ответа API:
 *     Создайте ObjectMapper с JavaTimeModule.
 *     Сериализуйте ProductResponseDto в JSON — это «ответ REST API».
 *     Убедитесь, что deletedAt отсутствует (оно только в Entity).
 *
 *   ЧАСТЬ 4 — Десериализация входящего запроса:
 *     Десериализуйте jsonCreateRequest (JSON от клиента) в ProductCreateDto.
 *     Создайте Entity через ProductMapper.toEntity() и выведите его поля.
 *
 *   ЧАСТЬ 5 — Симуляция полного цикла:
 *     Симулируйте полный REST-цикл:
 *       JSON-запрос → ProductCreateDto → ProductEntity (save) → ProductResponseDto → JSON-ответ
 *     Для «save» просто присвойте id=1L и createdAt=LocalDateTime.now() вручную.
 *
 * ПОДСКАЗКА:
 *   Заполните методы toDto() и toEntity() в классе ProductMapper.
 *   Не сериализуйте Entity напрямую — всегда через DTO.
 *   @JsonInclude(NON_NULL) на DTO исключит null-поля из ответа.
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;

public class Task07 {

    // Входящий JSON-запрос от клиента (POST /api/products)
    static final String JSON_CREATE_REQUEST = """
            {
              "name": "Смартфон",
              "price": 59999.0,
              "category_id": 2,
              "description": "Флагманский смартфон 2024 года"
            }
            """;

    public static void main(String[] args) throws Exception {
        // Создайте ObjectMapper с поддержкой дат
        // TODO: ObjectMapper mapper = new ObjectMapper();
        //       mapper.registerModule(new JavaTimeModule());
        //       mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // ЧАСТЬ 3 — Сериализация ответа API
        // Создайте тестовый Entity (как будто он уже сохранён в БД)
        // TODO: ProductEntity entity = new ProductEntity();
        //       entity.setId(1L);
        //       entity.setName("Смартфон");
        //       entity.setPrice(59999.0);
        //       entity.setCategoryId(2L);
        //       entity.setDescription("Флагманский смартфон 2024 года");
        //       entity.setCreatedAt(LocalDateTime.now());
        //       entity.setDeletedAt(null);  // не удалён

        // Смаппируйте Entity → DTO
        // TODO: ProductResponseDto dto = ProductMapper.toDto(entity);

        // Сериализуйте DTO в JSON (ответ API)
        // TODO: String responseJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        //       System.out.println("=== JSON ОТВЕТ API ===");
        //       System.out.println(responseJson);
        // Убедитесь: deletedAt отсутствует, поля называются по snake_case

        // ЧАСТЬ 4 — Десериализация входящего запроса
        // TODO: ProductCreateDto createDto = mapper.readValue(JSON_CREATE_REQUEST, ProductCreateDto.class);
        //       System.out.println("Запрос: " + createDto.getName() + ", " + createDto.getPrice());

        // Создайте Entity из DTO
        // TODO: ProductEntity newEntity = ProductMapper.toEntity(createDto);
        //       System.out.println("Entity.name = " + newEntity.getName());
        //       System.out.println("Entity.categoryId = " + newEntity.getCategoryId());

        // ЧАСТЬ 5 — Полный цикл
        // TODO: // симулируем «save»:
        //       newEntity.setId(42L);
        //       newEntity.setCreatedAt(LocalDateTime.now());
        //       // маппинг → ответ
        //       ProductResponseDto finalDto = ProductMapper.toDto(newEntity);
        //       String finalJson = mapper.writeValueAsString(finalDto);
        //       System.out.println("=== ПОЛНЫЙ ЦИКЛ — ОТВЕТ ===");
        //       System.out.println(finalJson);
    }
}

// ─── Entity (слой БД / JPA) ───────────────────────────────────────────────────
// Содержит все поля, включая служебные (deletedAt, version)
// НИКОГДА не сериализуется в JSON напрямую
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
        return null; // заглушка — замените реализацией
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
        return null; // заглушка — замените реализацией
    }
}

/*
 * Ожидаемый JSON-ответ (ЧАСТЬ 3, с @JsonInclude NON_NULL):
 * {
 *   "id" : 1,
 *   "name" : "Смартфон",
 *   "price" : 59999.0,
 *   "category_id" : 2,
 *   "description" : "Флагманский смартфон 2024 года",
 *   "created_at" : "2024-03-15T10:30:00"
 * }
 * (deletedAt отсутствует — оно только в Entity, не в DTO)
 */
