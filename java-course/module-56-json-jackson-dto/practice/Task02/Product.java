import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

// Готовый класс-модель
record Product(Long id, String name, double price) {}
