import com.fasterxml.jackson.databind.ObjectMapper;

// Готовый класс-модель (record Java 16+, Jackson 2.12+ поддерживает автоматически)
record Product(Long id, String name, double price) {}
