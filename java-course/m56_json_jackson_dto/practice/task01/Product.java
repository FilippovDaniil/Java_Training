package m56_json_jackson_dto.practice.task01;

import com.fasterxml.jackson.databind.ObjectMapper;

// Готовый класс-модель (record Java 16+, Jackson 2.12+ поддерживает автоматически)
record Product(Long id, String name, double price) {}
